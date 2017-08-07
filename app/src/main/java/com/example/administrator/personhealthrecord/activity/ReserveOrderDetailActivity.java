package com.example.administrator.personhealthrecord.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.AbstractObjectResult;
import com.example.administrator.personhealthrecord.bean.AppointmentBean;
import com.example.administrator.personhealthrecord.bean.ReserOrderBean;
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
    ReserOrderBean bean;
    private SweetAlertDialog pDialog;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_my_reserve_order_detail;
    }

    @Override
    protected void initData() {
        super.initData();
        bean = getIntent().getParcelableExtra("ReserOrderBean");
        Log.d(TAG, "initData: "+bean.getMedicalStatus());
        if(bean.getMedicalStatus().equals("正在预约"))
        {
            reserveOrderDetailCancle.setEnabled(true);
        }else
        {
            reserveOrderDetailCancle.setEnabled(false);
        }
        Glide.with(this)
                .load(Contract.ReserVeOrderHealthyCheckImageUrl + bean.getOrderId())
                .into(appointmentImage);
        reserveOrderDetailPackageName.setText(bean.getPackageName());
        reserveOrderDetailPackagedetail.setText(bean.getPackageDetail());
        reserveOrderDetailPackagePrice.setText("￥"+bean.getPackagePrice());
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:MM");
        appointmentTime.setText(format.format(new Date(bean.getStartTime()))+" - "+format.format(new Date(bean.getEndTime())));
        appointmentPhoneNumber.setText(bean.getPhoneNumber());
        reserveOrderDetailHospital.setText(bean.getHospitalName());
        appointmentStatus.setText(bean.getMedicalStatus());
        initToolbar("套餐详情：", true, null);
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        reserveOrderDetailCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancleHealthyCheckOrder();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        sm.setStatusBarTintEnabled(false);
    }

    public void cancleHealthyCheckOrder() {
        loding();
        Observer<AbstractObjectResult<ReserOrderBean>> observer = new Observer<AbstractObjectResult<ReserOrderBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AbstractObjectResult<ReserOrderBean> value) {
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
        service.cancleHealthyCheck(Contract.cookie,bean.getOrderId())
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
        reserveOrderDetailCancle.setEnabled(false);
        appointmentStatus.setText("已取消预约");
        pDialog.dismiss();
        pDialog = new SweetAlertDialog(ReserveOrderDetailActivity.this, SweetAlertDialog.SUCCESS_TYPE)
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
