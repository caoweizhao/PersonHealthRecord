package com.example.administrator.personhealthrecord.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.SearchBean;
import com.example.administrator.personhealthrecord.bean.SearchSection;
import com.example.administrator.personhealthrecord.contract.Contract;

import java.util.List;

/**
 * Created by Administrator on 2017-8-7.
 */

public class SearchAdapter extends BaseSectionQuickAdapter<SearchSection, BaseViewHolder> {

    private Context mContext;

    private String mQuery = "";

    public void setQuery(String query) {
        mQuery = query;
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public SearchAdapter(Context context, int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
        mContext = context;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SearchSection item) {
        helper.setText(R.id.header, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchSection item) {

        SpannableString nameString = new SpannableString(item.t.getName());
        int length = mQuery.length();
        int index = item.t.getName().indexOf(mQuery);
        ForegroundColorSpan f = new ForegroundColorSpan(Color.parseColor("#1c9e22"));
        nameString.setSpan(f, index, index + length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        SpannableString summaryString = new SpannableString(item.t.getSummary());
        index = item.t.getSummary().indexOf(mQuery);
        if (index != -1 && index + length < summaryString.length()) {
            summaryString.setSpan(f, index, index + length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        helper.setText(R.id.search_item__name, nameString)
                .setText(R.id.search_item_summary, summaryString);
        String imageUrl;
        if (item.t.getImageUrl().contains("http")) {
            imageUrl = item.t.getImageUrl();
        } else {
            if (item.t.getType() == SearchBean.TYPE_DOCTOR) {
                imageUrl = Contract.DoctorBase + item.t.getImageUrl();
            } else if (item.t.getType() == SearchBean.TYPE_HOSPITAL) {
                imageUrl = Contract.HospitalBase + item.t.getImageUrl();
            } else {
                imageUrl = Contract.MedicalBase + item.t.getImageUrl();
            }
        }

        Glide.with(mContext)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into((ImageView) helper.itemView.findViewById(R.id.search_item__img));
    }
}
