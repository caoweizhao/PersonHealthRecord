package com.example.administrator.personhealthrecord.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.AppointmentBean;
import com.example.administrator.personhealthrecord.contract.Contract;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    private AppointmentBean bean;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_appointment_detail;
    }

    @Override
    protected void initData() {
        super.initData();
        initToolbar("订单详情",true,null);
        bean=getIntent().getParcelableExtra("Appointment");
        Glide.with(this)
                .load(Contract.AppointMnetImageUrl+bean.getImageId())
                .into(appointmentImage);
        appointmentDoctorname.setText(bean.getDoctorName());
        appointmentDoctordetail.setText(bean.getDoctorSkill());
        appointmentHostpitalName.setText(bean.getHospitalName());
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:MM");
        appointmentTime.setText(format.format(new Date(bean.getStartTime()))+" - "+format.format(new Date(bean.getStartTime())));
        appointmentPhoneNumber.setText(bean.getPhoneNumber());
        appointmentStatus.setText(bean.getOrderStatus());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
