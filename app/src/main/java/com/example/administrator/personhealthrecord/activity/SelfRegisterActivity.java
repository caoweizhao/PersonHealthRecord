package com.example.administrator.personhealthrecord.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.ExpertBean;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.util.DialogUtil;
import com.example.administrator.personhealthrecord.util.RegexUtils;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;
import com.example.administrator.personhealthrecord.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public class SelfRegisterActivity extends BaseActivity {

    @BindView(R.id.expert_item_img)
    ImageView mDoctorPic;   //医生图片
    @BindView(R.id.expert_item_name)
    TextView mSelfRegisterDoctorName;   //医生名称
    @BindView(R.id.expert_item_title)
    TextView mSelfRegisterDoctorPosition;   //医生职位
    @BindView(R.id.expert_item_address)
    TextView mSelfRegisterDoctorWorkplace;  //医生工作地址
    @BindView(R.id.self_register_doctor_details)
    TextView mSelfRegisterDoctorDetails;    //医生详情
    @BindView(R.id.self_register_date)
    TextView mSelfRegisterDate;     //显示体检日期
    /*@BindView(R.id.self_register_input_date_picker)
    ImageView mSelfRegisterInputDatePicker; //弹出选择体检日期按钮*/
    @BindView(R.id.self_register_input_phone_number)
    EditText mSelfRegisterInputPhoneNumber; //输入手机号码
    @BindView(R.id.self_registered_get_vertify_code)
    Button mSelfRegisteredGetVertifyCode;   //获取验证码按钮
    @BindView(R.id.self_register_input_vertify_code)
    EditText mSelfRegisterInputVertifyCode; //输入验证码
    @BindView(R.id.self_registered_container)
    NestedScrollView mSelfRegisteredContainer;
    @BindView(R.id.self_register_btn)
    Button mSelfRegisterBtn;    //预约按钮
    @BindView(R.id.chose_time_spinner)
    Spinner mSpinner;
    private ExpertBean mExpertBean;
    private long mStartTime;
    private long mEndTime;
    private Date mDate;
    private Date mCurDate = new Date();
    SimpleDateFormat mSecondSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat mDaySdf = new SimpleDateFormat("yyyy-MM-d");

    SweetAlertDialog mDialog;

    private SelfRegisterService mService;

    Disposable mDisposable;
    private String mHour = "9:00";

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_self_register_layout;
    }

    @Override
    protected void initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setSharedElementsUseOverlay(true);
        }

        initToolbar("自助挂号", true, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mService = RetrofitUtil.getRetrofit().create(SelfRegisterService.class);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra("bundle");
            if (bundle != null) {
                mExpertBean = bundle.getParcelable(Contract.EXPERT_KEY);
                if (mExpertBean != null) {
                    initValue();
                }
            }
        }
        mDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
    }

    @Override
    protected void initEvents() {
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mHour = "9:00";
                        break;
                    case 1:
                        mHour = "10:00";
                        break;
                    case 2:
                        mHour = "15:00";
                        break;
                    case 3:
                        mHour = "16:00";
                        break;
                    default:
                        mHour = "00:00";
                        break;
                }

                try {
                    mDate = mSecondSdf.parse(mSelfRegisterDate.getText() + " " + mHour + ":00");
                    Log.d(TAG, "onItemSelected: " + mSecondSdf.format(mDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /**
         * 选择挂号日期
         */
        mSelfRegisterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar selectedDate = Calendar.getInstance();
                final Calendar now = Calendar.getInstance();

                Date d;
                try {
                    d = mDaySdf.parse(mSelfRegisterDate.getText().toString());
                    now.setTime(d);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(SelfRegisterActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        selectedDate.set(year, month, dayOfMonth);

                        Calendar now = Calendar.getInstance();
                        Date nowTime = new Date();
                        Date selectedTime = new Date();
                        try {
                            nowTime = mDaySdf.parse(mDaySdf.format(now.getTime()));
                            selectedTime = mDaySdf.parse(mDaySdf.format(selectedDate.getTime()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (nowTime.after(selectedTime)) {
                            ToastUtil.Toast("选择日期有误，请重新选择！");
                        } else {
                            try {
                                mSelfRegisterDate.setText(mDaySdf.format(selectedTime));
                                mDate = mSecondSdf.parse(mSelfRegisterDate.getText() + " " + mHour + ":00");
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE))
                        .show();
            }
        });

        mSelfRegisteredGetVertifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateVerifyCode();
            }
        });

        /**
         * 提交挂号预约
         */
        mSelfRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mDate.after(mCurDate)) {
                    ToastUtil.Toast("预约时间有误，请重新选择！");
                    mSelfRegisterBtn.setEnabled(true);
                    return;
                }
                /**
                 * 手机号不通过
                 */
                if (!validatePhoneNumber()) {
                    mSelfRegisterInputPhoneNumber.setError("请检查输入的手机号码是否有误！");
                    mSelfRegisterBtn.setEnabled(true);
                } else {
                    /**
                     * 验证码不通过
                     */
                    if (!validateVerifyCode()) {
                        mSelfRegisterInputVertifyCode.setError("验证码错误！");
                        mSelfRegisterBtn.setEnabled(true);
                    } else {
                        /**
                         * 已登录，提交预约
                         */
                        if (Contract.IsLogin.equals(Contract.Login)) {
                            Log.d("SelfRegisterActivity", "date" + mSecondSdf.format(mDate));
                            mStartTime = mDate.getTime();
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(mDate);
                            calendar.add(Calendar.HOUR_OF_DAY, 1);
                            mEndTime = calendar.getTimeInMillis();

                            mService.commitRegister(Contract.cookie, mExpertBean.getCode(),
                                    new Timestamp(mStartTime), new Timestamp(mEndTime),
                                    mSelfRegisterInputPhoneNumber.getText().toString())
                                    .subscribeOn(Schedulers.newThread())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<ResponseBody>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {
                                            mDisposable = d;
                                        }

                                        @Override
                                        public void onNext(ResponseBody value) {
                                            try {
                                                JSONObject jsonObject = new JSONObject(value.string());
                                                if (jsonObject.get("status").equals("success")) {
                                                    mDialog = new SweetAlertDialog(SelfRegisterActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                                                    mDialog.setCancelable(false);
                                                    mDialog.setConfirmText("我知道了")
                                                            .setTitleText("恭喜您，预约成功！")
                                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                                @Override
                                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                    mDialog.dismiss();
                                                                    finish();
                                                                }
                                                            }).show();
                                                } else {
                                                    mDialog = new SweetAlertDialog(SelfRegisterActivity.this, SweetAlertDialog.ERROR_TYPE);
                                                    mDialog.setCancelable(false);
                                                    mDialog.setConfirmText("我知道了")
                                                            .setContentText(jsonObject.get("message").toString())
                                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                                @Override
                                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                    mDialog.dismiss();
                                                                }
                                                            }).show();
                                                }
                                            } catch (JSONException | IOException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            showMessage(mSelfRegisteredContainer, e.getMessage());
                                            mSelfRegisterBtn.setEnabled(false);
                                        }

                                        @Override
                                        public void onComplete() {
                                            mSelfRegisterBtn.setEnabled(false);
                                        }
                                    });
                        } else {
                            /**
                             * 未登录，弹出对话框是否前往登录
                             */
                            DialogUtil.getLoginDialog(SelfRegisterActivity.this).show();
                        }
                    }
                }
            }
        });
    }

    private boolean validateVerifyCode() {
        return TextUtils.isDigitsOnly(mSelfRegisterInputVertifyCode.getText().toString())
                && mSelfRegisterInputVertifyCode.getText().toString()
                .equals(mSelfRegisteredGetVertifyCode.getText());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    /**
     * 验证手机号码是否正确
     */
    private boolean validatePhoneNumber() {
        return RegexUtils.isMobileExact(mSelfRegisterInputPhoneNumber.getText());
    }

    /**
     * 初始化医生数据
     */
    private void initValue() {
        String docImageUrl = Contract.DoctorBase + mExpertBean.getImageUrl();
        Glide.with(this)
                .load(docImageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mDoctorPic);
        mSelfRegisterDoctorName.setText(mExpertBean.getName());
        mSelfRegisterDoctorDetails.setText(mExpertBean.getQualification());
        mSelfRegisterDoctorPosition.setText(mExpertBean.getDoctorTitle());
        mSelfRegisterDoctorWorkplace.setText(mExpertBean.getAddress());
        mSelfRegisterDate.setText(mDaySdf.format(Calendar.getInstance().getTime()));
    }

    public void generateVerifyCode() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            sb.append(String.valueOf(random.nextInt(10)));
        }
        mSelfRegisteredGetVertifyCode.setText(sb);
    }

    interface SelfRegisterService {

        @FormUrlEncoded
        @POST("rro/new_order")
        Observable<ResponseBody> commitRegister(
                @Header("Cookie") String cookie,
                @Field("doctorCode") String doctorCode,
                @Field("startTime") Timestamp startTime,
                @Field("endTime") Timestamp endTime,
                @Field("phoneNumber") String phoneNumber);
    }

}

