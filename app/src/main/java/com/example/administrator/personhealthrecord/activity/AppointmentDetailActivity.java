package com.example.administrator.personhealthrecord.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.AbstractObjectResult;
import com.example.administrator.personhealthrecord.bean.AppointmentBean;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.mvp.reserve_order.api.ReserveOrderService;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;
import com.example.administrator.personhealthrecord.util.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class AppointmentDetailActivity extends BaseActivity {

    @BindView(R.id.appointment_image)
    ImageView mAppointmentImage;
    @BindView(R.id.appointment_doctorname)
    TextView mAppointmentDoctorName;
    @BindView(R.id.appointment_doctordetail)
    TextView mAppointmentDoctorDetail;
    @BindView(R.id.appointment_hostpital_name)
    TextView mAppointmentHospitalName;
    @BindView(R.id.reserve_order_detail_time)
    TextView mAppointmentTime;
    @BindView(R.id.reserve_order_detail_phoneNumber)
    TextView mAppointmentPhoneNumber;
    @BindView(R.id.reserve_order_detail_status)
    TextView mAppointmentStatus;
    @BindView(R.id.appointment_cancle)
    Button mAppointmentCancel;
    private SweetAlertDialog pDialog;
    private AppointmentBean mAppointmentBean;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_appointment_detail;
    }

    @Override
    protected void initData() {
        super.initData();
        initToolbar("订单详情", true, null);
        mAppointmentBean = getIntent().getParcelableExtra("Appointment");
        if (mAppointmentBean.getOrderStatus().equals("正在预约")) {
            mAppointmentCancel.setEnabled(true);
        } else {
            mAppointmentCancel.setEnabled(false);
        }
        Glide.with(this)
                .load(Contract.AppointmentImageUrl + mAppointmentBean.getImageId())
                .into(mAppointmentImage);
        mAppointmentDoctorName.setText(mAppointmentBean.getDoctorName());
        mAppointmentDoctorDetail.setText(mAppointmentBean.getDoctorSkill());
        mAppointmentHospitalName.setText(mAppointmentBean.getHospitalName());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        mAppointmentTime.setText(format.format(new Date(mAppointmentBean.getStartTime())) + " - " + format.format(new Date(mAppointmentBean.getEndTime())));
        mAppointmentPhoneNumber.setText(mAppointmentBean.getPhoneNumber());
        mAppointmentStatus.setText(mAppointmentBean.getOrderStatus());
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        mAppointmentCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAppointment();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sm.setStatusBarTintEnabled(false);
    }

    public void cancelAppointment() {
        showLoading();
        Observer<AbstractObjectResult<AppointmentBean>> observer = new Observer<AbstractObjectResult<AppointmentBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AbstractObjectResult<AppointmentBean> value) {
                if (value.getStatus().equals("success")) {
                    ReserveSuccess();
                } else {
                    pDialog.dismiss();
                    ToastUtil.Toast("取消失败");
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        Retrofit client = RetrofitUtil.getRetrofit();
        ReserveOrderService service = client.create(ReserveOrderService.class);
        service.cancelAppointment(Contract.cookie, mAppointmentBean.getRroId())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    public void showLoading() {
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public void ReserveSuccess() {
        mAppointmentCancel.setEnabled(false);
        mAppointmentStatus.setText("已取消预约");
        pDialog.dismiss();
        pDialog = new SweetAlertDialog(AppointmentDetailActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("成功取消！")
                .setConfirmText("我知道了")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        pDialog.dismiss();
                        finish();
                    }
                });
        pDialog.setCancelable(false);
        pDialog.show();
    }
}
