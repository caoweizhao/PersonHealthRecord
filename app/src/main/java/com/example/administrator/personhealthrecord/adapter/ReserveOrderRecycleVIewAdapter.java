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

import java.util.List;

/**
 * Created by andy on 2017/8/1.
 */

public class ReserveOrderRecycleVIewAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    private String mImageURL;
    private Context mContext;

    public ReserveOrderRecycleVIewAdapter(@LayoutRes int layoutResId, @Nullable List<T> data, Context context) {
        super(R.layout.abstract_item, data);
        this.mContext = context;
    }

    public ReserveOrderRecycleVIewAdapter(@LayoutRes int layoutResId, @Nullable List<T> data, Fragment context) {
        super(R.layout.abstract_item, data);
        this.mContext = context.getContext();
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {


//        helper.setText(R.id.abstract_item__title, item.getTitle())
//                .setText(R.id.abstract_item__summary, item.getSummary())
//                .setImageResource(R.id.abstract_item__img, R.mipmap.ic_launcher_round)
//                .setText(R.id.abstract_item_date,item.getdate());
//        ((TextView) helper.getView(R.id.abstract_item__title)).setText(item.getTitle());
//        ((TextView) helper.getView(R.id.abstract_item__summary)).setText(item.getSummary());
//        ((TextView) helper.getView(R.id.abstract_item_date)).setText(item.getdate());
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
                .into((ImageView) helper.getView(R.id.abstract_item__img));

    }
}