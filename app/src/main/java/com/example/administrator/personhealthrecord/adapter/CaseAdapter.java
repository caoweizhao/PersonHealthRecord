package com.example.administrator.personhealthrecord.adapter;

import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.CaseBean;
import com.example.administrator.personhealthrecord.view.DeletableLayout;

import java.util.List;

/**
 * Created by Administrator on 2017-8-5.
 */

public class CaseAdapter extends BaseQuickAdapter<CaseBean, BaseViewHolder> {

    public CaseAdapter(@Nullable List<CaseBean> data) {
        super(R.layout.case_item, data);
    }

    DeleteListener mListener;

    public interface ContentClickListener {
        void onContentClickListener(int position);
    }

    public interface DeleteListener {
        void onItemDeleted(int position,CaseBean caseBean);
    }

    public void setDeleteListener(DeleteListener listener) {
        this.mListener = listener;
    }

    public void setContentListener(ContentClickListener listener) {
        this.mContentListener = listener;
    }

    ContentClickListener mContentListener;

    @Override
    protected void convert(final BaseViewHolder helper, CaseBean item) {
        helper.setText(R.id.case_item__title, item.getCaseName())
                .setText(R.id.case_item_location, item.getHospitalName())
                .setText(R.id.case_item_date, item.getDate());
        final DeletableLayout deletableLayout = (DeletableLayout) helper.itemView.findViewById(R.id.deletableLayout);

        deletableLayout.setContentClickListener(new DeletableLayout.onContentClickListener() {
            @Override
            public void onContentClick() {
                if (mContentListener != null) {
                    mContentListener.onContentClickListener(helper.getLayoutPosition());
                }
            }
        });
        deletableLayout.setDeleteMenuListener(new DeletableLayout.OnDeleteMenuClickListener() {
            @Override
            public void onDeleteMenuClick() {
                if (mListener != null) {
                    mListener.onItemDeleted(helper.getLayoutPosition(),getItem(helper.getLayoutPosition()));
                }
                deletableLayout.closeDeleteMenu();
                Log.d("CaseAdapter", "onDeleteMenuClick" + helper.getLayoutPosition());
                deletableLayout.closeDeleteMenu();
                getData().remove(helper.getLayoutPosition());
                notifyItemRemoved(helper.getLayoutPosition());

            }
        });
    }
}
