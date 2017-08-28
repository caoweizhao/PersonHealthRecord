package com.example.administrator.personhealthrecord.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.AbstractReserveBean;
import com.example.administrator.personhealthrecord.bean.AppointmentBean;
import com.example.administrator.personhealthrecord.bean.ReserveOrderBean;
import com.example.administrator.personhealthrecord.contract.Contract;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by andy on 2017/8/1.
 */

public class ReserveOrderRecycleVIewAdapter<T extends AbstractReserveBean> extends BaseQuickAdapter<T, BaseViewHolder> {

    private String mImageURL;
    private Context mContext;
    private String mName;
    private String mTime;
    private String mHospitalName;
    private String mStatus;

    public ReserveOrderRecycleVIewAdapter(@LayoutRes int layoutResId, @Nullable List<T> data, Context context) {
        super(R.layout.healthy_reserve_order_item, data);
        this.mContext = context;
    }

    public ReserveOrderRecycleVIewAdapter(@LayoutRes int layoutResId, @Nullable List<T> data, Fragment context) {
        super(R.layout.healthy_reserve_order_item, data);
        this.mContext = context.getContext();
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        if (item instanceof ReserveOrderBean) {
            mName = item.getName();
            mImageURL = Contract.ReserveOrderHealthyCheckImageUrl + item.getImageId();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:MM");
            mTime = "体检时间:" + sdf.format(item.getStartTime());
            mHospitalName = "体检医院:" + item.getHospitalNameTotal();
            mStatus = ((ReserveOrderBean) item).getMedicalStatus();
        } else if (item instanceof AppointmentBean) {
            mName = ((AppointmentBean) item).getDoctorName();
            mImageURL = Contract.AppointmentImageUrl + ((AppointmentBean) item).getRroId();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:MM");
            mTime = "门诊时间:" + sdf.format(item.getStartTime());
            mHospitalName = "体检医院:" + item.getHospitalNameTotal();
            mStatus = ((AppointmentBean) item).getOrderStatus();
        }
        helper.setText(R.id.health_check_reserve_order_title, mName)
                .setText(R.id.health_check_reserve_order_time, mTime)
                .setText(R.id.health_check_reserve_order_hospital, mHospitalName)
                .setText(R.id.health_check_reserve_order_cancle, mStatus);
        Glide.with(mContext)
                .load(mImageURL)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into((ImageView) helper.getView(R.id.health_check_reserve_order_img));

    }
}