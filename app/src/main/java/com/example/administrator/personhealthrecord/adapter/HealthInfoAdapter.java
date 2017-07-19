package com.example.administrator.personhealthrecord.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

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
        helper.setText(R.id.health_info_item_title, item.getTitle())
                .setText(R.id.health_info_item_summary, item.getSummary())
                .setText(R.id.health_info_item_like_count, "10")
                .setText(R.id.health_info_item_comment, "20")
                .setImageResource(R.id.health_info_item_img, R.mipmap.ic_launcher_round)
                .setOnClickListener(R.id.health_info_item_like_count, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (v.isSelected()) {
                            v.setSelected(false);
                        } else v.setSelected(true);
                    }
                })
        ;
    }
}
