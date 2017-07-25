package com.example.administrator.personhealthrecord.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.HospitalBean;
import com.example.administrator.personhealthrecord.contract.Contract;

import java.util.List;

/**
 * Created by Administrator on 2017-7-20.
 */

public class HospitalAdapter extends BaseQuickAdapter<HospitalBean, BaseViewHolder> {
    private String mImageURL;
    public HospitalAdapter(@LayoutRes int layoutResId, @Nullable List<HospitalBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HospitalBean item) {
        if(item.getImageUrl().contains("http"))
            mImageURL=item.getImageUrl();
        else
            mImageURL= Contract.HospitalBase+item.getImageUrl();
        helper.setText(R.id.hospital_item_clazz, item.getLevel())
                .setText(R.id.hospital_item__name, item.getName())
                .setText(R.id.hospital_item_address, item.getAddress())
                .addOnClickListener(R.id.hospital_item_layout);
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
                .into((ImageView) helper.getView(R.id.hospital_item__img));
    }
}
