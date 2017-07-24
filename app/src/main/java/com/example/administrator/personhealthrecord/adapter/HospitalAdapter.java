package com.example.administrator.personhealthrecord.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.HospitalBean;

import java.util.List;

/**
 * Created by Administrator on 2017-7-20.
 */

public class HospitalAdapter extends BaseQuickAdapter<HospitalBean, BaseViewHolder> {

    public HospitalAdapter(@LayoutRes int layoutResId, @Nullable List<HospitalBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HospitalBean item) {
        helper.setText(R.id.hospital_item_clazz, item.getClazz())
                .setText(R.id.hospital_item__name, item.getName())
                .setText(R.id.hospital_item_address, item.getAddress())
                .addOnClickListener(R.id.hospital_item_layout);
    }
}
