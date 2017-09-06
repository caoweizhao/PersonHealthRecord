package com.example.administrator.personhealthrecord.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.CaseBean;
import com.example.administrator.personhealthrecord.bean.UserInfoBean;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.util.DialogUtil;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;
import com.example.administrator.personhealthrecord.util.ToastUtil;
import com.google.gson.Gson;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public class AddCaseActivity extends BaseActivity {

    @BindView(R.id.add_case_name)
    TextView mAddCaseNameText;
    @BindView(R.id.add_case_age_text)
    TextView mAddCaseAgeText;
    @BindView(R.id.add_case_gender_text)
    TextView mAddCaseGenderText;
    @BindView(R.id.edit_hospital)
    EditText mEditHospital;
    @BindView(R.id.edit_condition)
    EditText mEditCondition;
    @BindView(R.id.prescription)
    TextView mPrescription;
    @BindView(R.id.edit_prescription)
    EditText mEditPrescription;
    @BindView(R.id.edit_schedule)
    EditText mEditSchedule;
    @BindView(R.id.remark)
    TextView mRemark;
    @BindView(R.id.edit_remark)
    EditText mEditRemark;
    @BindView(R.id.add_case_commit)
    Button mAddCaseCommit;
    @BindView(R.id.add_case_date)
    TextView mDateTextView;

    CaseBean mCaseBean = new CaseBean();
    AddCaseService mService;
    SweetAlertDialog mLoadingDialog;
    SweetAlertDialog mSuccessDialog;
    List<Disposable> mDisposables = new ArrayList<>();
    Calendar mCalendar = Calendar.getInstance();

    @Override
    protected int getLayoutRes() {
        return R.layout.add_case;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar("添加病历", true, null);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        mDateTextView.setText(sdf.format(mCalendar.getTime()));
        mService = RetrofitUtil.getRetrofit().create(AddCaseService.class);
        mLoadingDialog = DialogUtil.getLoadingDialog(this);
        mSuccessDialog = DialogUtil.getSuccessDialog(this, "添加成功！", true);

        mService.getSelfInfo(Contract.cookie)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ResponseBody, UserInfoBean>() {
                    @Override
                    public UserInfoBean apply(ResponseBody body) throws Exception {
                        JSONObject jsonObject = new JSONObject(body.string());
                        if (jsonObject.get("status").equals("success")) {
                            Gson gson = new Gson();
                            UserInfoBean userInfoBean = gson.fromJson(jsonObject.get("object").toString(),
                                    UserInfoBean.class);
                            return userInfoBean;
                        }
                        return new UserInfoBean();
                    }

                }).subscribe(new Observer<UserInfoBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(UserInfoBean value) {
                mCaseBean.setName(value.getName());
                mCaseBean.setAge(Integer.valueOf(!TextUtils.isEmpty(value.getAge()) ? value.getAge() : "0"));
                mCaseBean.setGender(value.getGender());
                mAddCaseNameText.setText(value.getName());
                mAddCaseGenderText.setText(value.getGender());
                mAddCaseAgeText.setText(value.getAge());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        /**
         * 就诊医院
         */
        RxTextView.textChanges(mEditHospital)
                .filter(new Predicate<CharSequence>() {
                    @Override
                    public boolean test(CharSequence sequence) throws Exception {
                        return sequence != null;
                    }
                }).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence sequence) throws Exception {
                mCaseBean.setHospitalName(sequence.toString());
            }
        });
        //就诊时间
        RxView.clicks(mDateTextView).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                new DatePickerDialog(AddCaseActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mCalendar.set(year, month, dayOfMonth);
                        Calendar now = Calendar.getInstance();
                        if (mCalendar.after(now)) {
                            ToastUtil.Toast("选择日期不能在当前日期之前！");
                        } else {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            mCaseBean.setTime(mCalendar.getTime().getTime());
                            mDateTextView.setText(sdf.format(mCalendar.getTime()));
                        }

                    }
                }, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DATE))
                        .show();
            }
        });

        /**
         * 身体状况
         */
        RxTextView.textChanges(mEditCondition).filter(new Predicate<CharSequence>() {
            @Override
            public boolean test(CharSequence sequence) throws Exception {
                return sequence != null;
            }
        }).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence sequence) throws Exception {
                mCaseBean.setPhysicalCondition(sequence.toString());
            }
        });

        /**
         * 药物处方
         */
        RxTextView.textChanges(mEditPrescription).filter(new Predicate<CharSequence>() {
            @Override
            public boolean test(CharSequence sequence) throws Exception {
                return sequence != null;
            }
        }).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence sequence) throws Exception {
                mCaseBean.setPrescriptionMedicine(sequence.toString());
            }
        });
        /**
         * 治疗日程
         */
        RxTextView.textChanges(mEditSchedule).filter(new Predicate<CharSequence>() {
            @Override
            public boolean test(CharSequence sequence) throws Exception {
                return sequence != null;
            }
        }).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence sequence) throws Exception {
                mCaseBean.setTreatmentSchedule(sequence.toString());
            }
        });

        /**
         * 待办事件
         */
        RxTextView.textChanges(mEditRemark).filter(new Predicate<CharSequence>() {
            @Override
            public boolean test(CharSequence sequence) throws Exception {
                return sequence != null;
            }
        }).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence sequence) throws Exception {
                mCaseBean.setTodoSomething(sequence.toString());
            }
        });

        /**
         * 提交
         */
        mAddCaseCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadingDialog.show();
                mService.addCase(mCaseBean, Contract.cookie)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Observer<ResponseBody>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                mDisposables.add(d);
                            }

                            @Override
                            public void onNext(ResponseBody value) {

                                // TODO: 2017-8-5 根据返回信息判断是否成功    mSuccessDialog.show();
                                try {
                                    JSONObject jsonObject = new JSONObject(value.string());
                                    if (jsonObject.get("status").equals("success")) {
                                        mSuccessDialog.show();
                                    } else {
                                        ToastUtil.Toast("提交失败");
                                    }
                                } catch (JSONException | IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                ToastUtil.Toast("提交失败！");
                                mLoadingDialog.dismiss();
                            }

                            @Override
                            public void onComplete() {
                                mLoadingDialog.dismiss();
                            }
                        });
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (mDisposables != null) {
            for (Disposable mDisposable : mDisposables) {
                mDisposable.dispose();
            }
        }
        super.onDestroy();
    }

    interface AddCaseService {

        @GET("user_info/search")
        Observable<ResponseBody> getSelfInfo(@Header("Cookie") String cookie);

        @POST("medical_record/new_record")
        Observable<ResponseBody> addCase(@Body CaseBean caseBean, @Header("Cookie") String cookie);
    }
}
