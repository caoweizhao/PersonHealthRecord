package com.example.administrator.personhealthrecord.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.PackageBean;
import com.example.administrator.personhealthrecord.contract.Contract;

import java.util.List;

/**
 * Created by andy on 2017/7/27.
 */

public class PackageInfoAdapter extends BaseQuickAdapter<PackageBean, BaseViewHolder> {
    private String imageURL;

    public PackageInfoAdapter(@LayoutRes int layoutResId, @Nullable List<PackageBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, PackageBean item) {
        helper.setText(R.id.health_check_item__title, item.getName())
                .setText(R.id.health_check_item__summary, item.getSummary())
                .setText(R.id.health_check_item_money, mContext.getString(R.string.yuan) + item.getPackagePrice())
                .setText(R.id.health_check_item_reserve, mContext.getString(R.string.had_reserve) + item.getAllocatedQuantity())
                .addOnClickListener(R.id.health_check_item_package_itemId);
        helper.getView(R.id.health_check_item_package_itemId).setClickable(true);
        imageURL = Contract.PackageImageBase + item.getImageUrl();
        Glide.with(mContext).load(imageURL)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into((ImageView) helper.getView(R.id.health_check_item__img));

    }
}
