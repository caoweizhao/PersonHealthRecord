package com.example.administrator.personhealthrecord.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.personhealthrecord.R;

import java.util.List;

/**
 * Created by Administrator on 2017-7-19.
 */

public class AbstractItemAdapter<T extends AbstractItem> extends BaseQuickAdapter<T, BaseViewHolder> {

    public AbstractItemAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        helper.setText(R.id.health_info_item_title, item.getTitle())
                .setText(R.id.health_info_item_summary, item.getSummary())
                .setText(R.id.health_info_item_like_count, "10")
                .setText(R.id.health_info_item_comment, "20")
                .setImageResource(R.id.health_info_item_img, R.mipmap.ic_launcher_round)
        ;
    }
}
