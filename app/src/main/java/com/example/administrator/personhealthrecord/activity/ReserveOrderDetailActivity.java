package com.example.administrator.personhealthrecord.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.ReserOrderBean;
import com.example.administrator.personhealthrecord.contract.Contract;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andy on 2017/8/4.
 */

public class ReserveOrderDetailActivity extends BaseActivity {


    @BindView(R.id.appointment_image)
    ImageView appointmentImage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.reserve_order_detail_packageName)
    TextView reserveOrderDetailPackageName;
    @BindView(R.id.reserve_order_detail_packagedetail)
    TextView reserveOrderDetailPackagedetail;
    @BindView(R.id.reserve_order_detail_package_price)
    TextView reserveOrderDetailPackagePrice;
    @BindView(R.id.reserve_order_detail_time)
    TextView appointmentTime;
    @BindView(R.id.reserve_order_detail_phoneNumber)
    TextView appointmentPhoneNumber;
    @BindView(R.id.reserve_order_detail_hospital)
    TextView reserveOrderDetailHospital;
    @BindView(R.id.reserve_order_detail_status)
    TextView appointmentStatus;
    @BindView(R.id.reserve_order_detail_cancle)
    Button reserveOrderDetailCancle;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_my_reserve_order_detail;
    }

    @Override
    protected void initData() {
        super.initData();
        ReserOrderBean bean = getIntent().getParcelableExtra("ReserOrderBean");
        Glide.with(this)
                .load(Contract.ReserVeOrderHealthyCheckImageUrl + bean.getOrderId())
                .into(appointmentImage);
        reserveOrderDetailPackageName.setText(bean.getPackageName());
        reserveOrderDetailPackagedetail.setText(bean.getPackageDetail());
        reserveOrderDetailPackagePrice.setText(bean.getPackagePrice()+"￥");
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:MM");
        appointmentTime.setText(format.format(new Date(bean.getStartTime()))+format.format(new Date(bean.getEndTime())));
        appointmentPhoneNumber.setText(bean.getPhoneNumber());
        reserveOrderDetailHospital.setText(bean.getHospitalName());
        appointmentStatus.setText(bean.getMedicalStatus());
        initToolbar("套餐详情：", true, null);
    }

    @Override
    protected void initEvents() {
        super.initEvents();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
