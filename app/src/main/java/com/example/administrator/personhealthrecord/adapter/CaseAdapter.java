package com.example.administrator.personhealthrecord.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.CaseBean;

import java.util.List;

/**
 * Created by Administrator on 2017-8-5.
 */

public class CaseAdapter extends BaseQuickAdapter<CaseBean, BaseViewHolder> {

    public CaseAdapter(@Nullable List<CaseBean> data) {
        super(R.layout.case_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CaseBean item) {
        helper.setText(R.id.case_item__title,"病历"+item.getRecordNumber())
                .setText(R.id.case_item_location,item.getHospitalName())
                .setText(R.id.case_item_date,item.getDate());
    }
}
