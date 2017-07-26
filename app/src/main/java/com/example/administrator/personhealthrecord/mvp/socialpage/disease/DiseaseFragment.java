package com.example.administrator.personhealthrecord.mvp.socialpage.disease;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.adapter.AbstractItemAdapter;
import com.example.administrator.personhealthrecord.mvp.socialpage.SocialPageBaseFragment;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiseaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiseaseFragment extends SocialPageBaseFragment {

    @BindView(R.id.disease_page_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.disease_page_swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    AbstractItemAdapter mAdapter;

    public static DiseaseFragment newInstance() {
        return new DiseaseFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_disease_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new AbstractItemAdapter(R.layout.abstract_item, null, this);
        mRecyclerView.setAdapter(mAdapter);
    }
}
