package com.example.administrator.personhealthrecord.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.AbstractReserveBean;
import com.example.administrator.personhealthrecord.bean.AppointmentBean;
import com.example.administrator.personhealthrecord.bean.ReserOrderBean;
import com.example.administrator.personhealthrecord.contract.Contract;

import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by andy on 2017/8/1.
 */

public class ReserveOrderRecycleVIewAdapter<T extends AbstractReserveBean> extends BaseQuickAdapter<T, BaseViewHolder> {

    private String mImageURL;
    private Context mContext;
    private String name;
    private String time;
    private String hostpitalName;
    private String status;

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
            if(item instanceof ReserOrderBean)
            {
                name=item.getName();
                mImageURL= Contract.ReserVeOrderHealthyCheckImageUrl+item.getImageId();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:MM");
                time="体检时间:"+sdf.format(item.getStartTime());
                hostpitalName="体检医院:"+item.getHosPitalNameTotal();
                status=((ReserOrderBean) item).getMedicalStatus();
            }else if(item instanceof AppointmentBean)
            {
                name=((AppointmentBean) item).getDoctorName();
                mImageURL= Contract.AppointMnetImageUrl+((AppointmentBean) item).getRroId();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:MM");
                time="门诊时间:"+sdf.format(item.getStartTime());
                hostpitalName="体检医院:"+item.getHosPitalNameTotal();
                status=((AppointmentBean) item).getOrderStatus();
            }

//        helper.setText(R.id.abstract_item__title, item.getTitle())
//                .setText(R.id.abstract_item__summary, item.getSummary())
//                .setImageResource(R.id.abstract_item__img, R.mipmap.ic_launcher_round)
//                .setText(R.id.abstract_item_date,item.getdate());
//        ((TextView) helper.getView(R.id.abstract_item__title)).setText(item.getTitle());
//        ((TextView) helper.getView(R.id.abstract_item__summary)).setText(item.getSummary());
//        ((TextView) helper.getView(R.id.abstract_item_date)).setText(item.getdate());
        helper.setText(R.id.health_check_reserve_order_title,name)
                .setText(R.id.health_check_reserve_order_time,time)
                .setText(R.id.health_check_reserve_order_hospital,hostpitalName)
        .setText(R.id.health_check_reserve_order_cancle,status);
        Glide.with(mContext)
                .load(mImageURL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model,
                                               Target<GlideDrawable> target,
                                               boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model,
                                                   Target<GlideDrawable> target,
                                                   boolean isFromMemoryCache,
                                                   boolean isFirstResource) {
                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into((ImageView) helper.getView(R.id.health_check_reserve_order_img));

    }
}