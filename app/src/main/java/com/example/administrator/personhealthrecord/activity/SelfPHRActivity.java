package com.example.administrator.personhealthrecord.activity;

import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.SuperscriptSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.PHRBean;
import com.example.administrator.personhealthrecord.bean.UserInfoBean;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.mvp.registandlogin.LoginActivity;
import com.example.administrator.personhealthrecord.util.AnimateUtil;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Administrator on 2017-8-1.
 */

public class SelfPHRActivity extends BaseActivity {

    Disposable mDisposable;

    @BindView(R.id.edit_phr)
    ImageView mImageView;

    SelfPHRService mService;
    SweetAlertDialog mDialog;
    @BindView(R.id.phr_name)
    TextView mPhrName;
    @BindView(R.id.phr_age)
    TextView mPhrAge;
    @BindView(R.id.phr_body_mass_index)
    TextView mPhrBodyMassIndex;
    @BindView(R.id.phr_tall)
    TextView mPhrTall;
    @BindView(R.id.phr_weight)
    TextView mPhrWeight;
    @BindView(R.id.blood_type)
    TextView mBloodType;
    @BindView(R.id.heart_rate)
    TextView mHeartRate;
    @BindView(R.id.phr_systolic)
    TextView mPressureUp;
    @BindView(R.id.phr_diastolic)
    TextView mPressureDown;
    @BindView(R.id.phr_smoking_amount)
    TextView mPhrSmokingAmount;
    @BindView(R.id.drinking_type)
    TextView mDrinkingType;
    @BindView(R.id.drinking_amount)
    TextView mDrinkingAmount;
    @BindView(R.id.drinking_frequency)
    TextView mDrinkingFrequency;
    @BindView(R.id.phr_exercise_status)
    TextView mPhrExerciseStatus;
    @BindView(R.id.phr_allergy_history)
    TextView mPhrAllergyHistory;
    @BindView(R.id.phr_body_mass_index_unit)
    TextView mPhrBodyMassIndexUnit;
    @BindView(R.id.container_)
    View view;
    PHRBean mPhrBean;
    UserInfoBean mUserInfoBean;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_self_phr;
    }

    @Override
    protected void initData() {

        SpannableString ss = new SpannableString("kg/m2");
        SuperscriptSpan sss = new SuperscriptSpan();
        ss.setSpan(sss, 4, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        mPhrBodyMassIndexUnit.setText(ss);

        mService = RetrofitUtil.getRetrofit().create(SelfPHRService.class);
        initToolbar("查看个人PHR", true, null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSelfPHR();
    }

    private void getSelfPHR() {
        if (Contract.IsLogin.equals(Contract.Login)) {
            view.post(new Runnable() {
                @Override
                public void run() {
                    AnimateUtil.createCircularRevealFromTopLeftToRightBottom(view);
                }
            });
            Observable<PHRBean> observable1 = mService.getSelfPHR(Contract.cookie)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(new Function<ResponseBody, PHRBean>() {
                        @Override
                        public PHRBean apply(ResponseBody body) throws Exception {
                            JSONObject jsonObject = new JSONObject(body.string());
                            if (jsonObject.get("status").equals("success")) {
                                Gson gson = new Gson();
                                PHRBean phrBean = gson.fromJson(jsonObject.get("object").toString(),
                                        PHRBean.class);
                                return phrBean;
                            }
                            return new PHRBean();
                        }
                    });
            Observable<UserInfoBean> observable2 = mService.getSelfInfo(Contract.cookie)
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

                    });

            Observable.zip(observable1, observable2, new BiFunction<PHRBean, UserInfoBean, PHRClass>() {
                @Override
                public PHRClass apply(PHRBean bean, UserInfoBean bean2) throws Exception {
                    return new PHRClass(bean, bean2);
                }
            }).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<PHRClass>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            mDisposable = d;
                        }

                        @Override
                        public void onNext(PHRClass value) {
                            initValues(value.mPHRBean, value.mUserInfoBean);
                        }

                        @Override
                        public void onError(Throwable e) {
                            showMessage(mPhrAllergyHistory, e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        } else {
            mDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("登录提醒")
                    .setContentText("当前操作需要您登录，是否前往登录？")
                    .setCancelText("不了")
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            mDialog.dismiss();
                            finish();
                        }
                    }).setConfirmText("好的")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            mDialog.dismiss();
                            Intent intent = new Intent(SelfPHRActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });
            mDialog.setCancelable(false);
            mDialog.show();
        }
    }

    private void initValues(PHRBean bean, UserInfoBean bean2) {
        mPhrBean = bean;
        mUserInfoBean = bean2;
        mPhrName.setText(bean2.getName());
        mPhrAge.setText(bean2.getAge());

        mPhrBodyMassIndex.setText(bean.getBodyMassIndex());
        mPhrTall.setText(bean.getHeight());
        mPhrWeight.setText(bean.getWeight());
        mPhrAllergyHistory.setText(bean.getMedicineAllergy());
        mDrinkingAmount.setText(bean.getAlcoholVolume());
        mDrinkingFrequency.setText(bean.getDrinkingFrequency());
        mDrinkingType.setText(bean.getDrinkingType());
        mBloodType.setText(bean.getBloodType());
        mPhrSmokingAmount.setText(bean.getSmokingVolume());
        mPhrExerciseStatus.setText(bean.getPhysicalExercise());
        mHeartRate.setText(bean.getHeartRate());
        mPressureUp.setText(bean.getBloodPressureUp());
        mPressureDown.setText(bean.getBloodPressureDown());
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimateUtil.createCircularReveal(mImageView);
                Intent intent = new Intent(SelfPHRActivity.this, EditPHRActivity.class);
                intent.putExtra("phr", mPhrBean);
                intent.putExtra("user", mUserInfoBean);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    interface SelfPHRService {

        @GET("user_info/search")
        Observable<ResponseBody> getSelfInfo(@Header("Cookie") String cookie);

        @GET("phr/search")
        Observable<ResponseBody> getSelfPHR(@Header("Cookie") String cookie);
    }

    class PHRClass {
        PHRBean mPHRBean;
        UserInfoBean mUserInfoBean;

        public PHRClass(PHRBean phrBean, UserInfoBean userInfoBean) {
            this.mPHRBean = phrBean;
            this.mUserInfoBean = userInfoBean;
        }
    }
}
