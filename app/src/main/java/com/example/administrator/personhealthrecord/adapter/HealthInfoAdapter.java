package com.example.administrator.personhealthrecord.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.HealthInfo;

import java.util.List;

/**
 * Created by Administrator on 2017-7-19.
 */

public class HealthInfoAdapter extends BaseQuickAdapter<HealthInfo, BaseViewHolder> {

    public HealthInfoAdapter(@LayoutRes int layoutResId, @Nullable List<HealthInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HealthInfo item) {
        helper.setText(R.id.abstract_item__title, item.getTitle())
                .setText(R.id.abstract_item__summary, item.getSummary())
                .setImageResource(R.id.abstract_item__img, R.mipmap.ic_launcher_round)
        ;
    }
}
