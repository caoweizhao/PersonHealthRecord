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
import com.example.administrator.personhealthrecord.bean.ReserveOrderBean;
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

/**
 * Created by andy on 2017/8/4.
 */

public class ReserveOrderDetailActivity extends BaseActivity {


    @BindView(R.id.appointment_image)
    ImageView mAppointmentImage;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.reserve_order_detail_packageName)
    TextView mReserveOrderDetailPackageName;
    @BindView(R.id.reserve_order_detail_packagedetail)
    TextView mReserveOrderDetailPackageDetail;
    @BindView(R.id.reserve_order_detail_package_price)
    TextView mReserveOrderDetailPackagePrice;
    @BindView(R.id.reserve_order_detail_time)
    TextView mAppointmentTime;
    @BindView(R.id.reserve_order_detail_phoneNumber)
    TextView mAppointmentPhoneNumber;
    @BindView(R.id.reserve_order_detail_hospital)
    TextView mReserveOrderDetailHospital;
    @BindView(R.id.reserve_order_detail_status)
    TextView mAppointmentStatus;
    @BindView(R.id.reserve_order_detail_cancle)
    Button mReserveOrderDetailCancel;
    ReserveOrderBean mReserveOrderBean;
    private SweetAlertDialog pDialog;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_my_reserve_order_detail;
    }

    @Override
    protected void initData() {
        super.initData();
        mReserveOrderBean = getIntent().getParcelableExtra("ReserveOrderBean");
        Log.d(TAG, "initData: " + mReserveOrderBean.getMedicalStatus());
        if (mReserveOrderBean.getMedicalStatus().equals("正在预约")) {
            mReserveOrderDetailCancel.setEnabled(true);
        } else {
            mReserveOrderDetailCancel.setEnabled(false);
        }
        Glide.with(this)
                .load(Contract.ReserveOrderHealthyCheckImageUrl + mReserveOrderBean.getOrderId())
                .into(mAppointmentImage);
        mReserveOrderDetailPackageName.setText(mReserveOrderBean.getPackageName());
        mReserveOrderDetailPackageDetail.setText(mReserveOrderBean.getPackageDetail());
        mReserveOrderDetailPackagePrice.setText("￥" + mReserveOrderBean.getPackagePrice());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:MM");
        mAppointmentTime.setText(format.format(new Date(mReserveOrderBean.getStartTime())) + " - " + format.format(new Date(mReserveOrderBean.getEndTime())));
        mAppointmentPhoneNumber.setText(mReserveOrderBean.getPhoneNumber());
        mReserveOrderDetailHospital.setText(mReserveOrderBean.getHospitalName());
        mAppointmentStatus.setText(mReserveOrderBean.getMedicalStatus());
        initToolbar("套餐详情：", true, null);
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        mReserveOrderDetailCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelHealthyCheckOrder();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sm.setStatusBarTintEnabled(false);
    }

    public void cancelHealthyCheckOrder() {
        loading();
        Observer<AbstractObjectResult<ReserveOrderBean>> observer = new Observer<AbstractObjectResult<ReserveOrderBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AbstractObjectResult<ReserveOrderBean> value) {
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
        service.cancelHealthyCheck(Contract.cookie, mReserveOrderBean.getOrderId())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void loading() {
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public void ReserveSuccess() {
        mReserveOrderDetailCancel.setEnabled(false);
        mAppointmentStatus.setText("已取消预约");
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
