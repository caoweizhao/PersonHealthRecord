package com.example.administrator.personhealthrecord.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.ExpertBean;
import com.example.administrator.personhealthrecord.contract.Contract;

import java.util.List;

/**
 * Created by Administrator on 2017-7-20.
 */

public class ExpertAdapter extends BaseQuickAdapter<ExpertBean, BaseViewHolder> {
    private String mImageURL;

    public ExpertAdapter(@LayoutRes int layoutResId, @Nullable List<ExpertBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExpertBean item) {
        if (item.getImageUrl().contains("http"))
            mImageURL = item.getImageUrl();
        else {
            mImageURL = Contract.DoctorBase + item.getImageUrl();
        }
        helper.setText(R.id.expert_item_address, item.getAddress())
                .setText(R.id.expert_item_name, item.getName())
                .setText(R.id.expert_item_title, item.getDoctorTitle())
                .addOnClickListener(R.id.expert_item_layout);
        Glide.with(mContext)
                .load(mImageURL)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into((ImageView) helper.getView(R.id.expert_item_img));
    }

}
