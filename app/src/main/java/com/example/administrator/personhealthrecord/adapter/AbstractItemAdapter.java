package com.example.administrator.personhealthrecord.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.AbstractItem;

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
        helper.setText(R.id.abstract_item__title, item.getTitle())
                .setText(R.id.abstract_item__summary, item.getSummary())
                .setImageResource(R.id.abstract_item__img, R.mipmap.ic_launcher_round)
        ;
    }
}
