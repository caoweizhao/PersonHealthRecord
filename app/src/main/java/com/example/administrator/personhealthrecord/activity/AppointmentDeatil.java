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
import com.example.administrator.personhealthrecord.mvp.reserveorder.api.ReserveOrderService;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;
import com.example.administrator.personhealthrecord.util.ToastUitl;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class AppointmentDeatil extends BaseActivity {

    @BindView(R.id.appointment_image)
    ImageView appointmentImage;
    @BindView(R.id.appointment_doctorname)
    TextView appointmentDoctorname;
    @BindView(R.id.appointment_doctordetail)
    TextView appointmentDoctordetail;
    @BindView(R.id.appointment_hostpital_name)
    TextView appointmentHostpitalName;
    @BindView(R.id.reserve_order_detail_time)
    TextView appointmentTime;
    @BindView(R.id.reserve_order_detail_phoneNumber)
    TextView appointmentPhoneNumber;
    @BindView(R.id.reserve_order_detail_status)
    TextView appointmentStatus;
    @BindView(R.id.appointment_cancle)
    Button appointmentCancle;
    private SweetAlertDialog pDialog;
    private AppointmentBean bean;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_appointment_detail;
    }

    @Override
    protected void initData() {
        super.initData();
        initToolbar("订单详情", true, null);
        bean = getIntent().getParcelableExtra("Appointment");
        if(bean.getOrderStatus().equals("正在预约"))
        {
            appointmentCancle.setEnabled(true);
        }else
        {
            appointmentCancle.setEnabled(false);
        }
        Glide.with(this)
                .load(Contract.AppointMnetImageUrl + bean.getImageId())
                .into(appointmentImage);
        appointmentDoctorname.setText(bean.getDoctorName());
        appointmentDoctordetail.setText(bean.getDoctorSkill());
        appointmentHostpitalName.setText(bean.getHospitalName());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:MM");
        appointmentTime.setText(format.format(new Date(bean.getStartTime())) + " - " + format.format(new Date(bean.getStartTime())));
        appointmentPhoneNumber.setText(bean.getPhoneNumber());
        appointmentStatus.setText(bean.getOrderStatus());
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        appointmentCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancleAppoitnment();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void cancleAppoitnment() {
        loding();
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
                    ToastUitl.Toast("取消失败");
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
        service.cancleAppointMnet(Contract.cookie,bean.getRroId())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void loding() {
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public void ReserveSuccess() {
        appointmentCancle.setEnabled(false);
        appointmentStatus.setText("已取消预约");
        pDialog.dismiss();
        pDialog = new SweetAlertDialog(AppointmentDeatil.this, SweetAlertDialog.SUCCESS_TYPE)
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