package com.example.administrator.personhealthrecord.mvp.reserve_order.healthcheckorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.activity.ReserveOrderDetailActivity;
import com.example.administrator.personhealthrecord.adapter.ReserveOrderRecycleVIewAdapter;
import com.example.administrator.personhealthrecord.bean.ReserveOrderBean;
import com.example.administrator.personhealthrecord.mvp.reserve_order.IReserveOrderView;
import com.example.administrator.personhealthrecord.mvp.reserve_order.ReserveOrderPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by andy on 2017/7/31.
 */

public class HealthyCheckOrderFragment extends IReserveOrderView {
    private static final String TAG = "HealthyCheckOrder";
    @BindView(R.id.reserve_order_recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.health_check_reserve_order_ProgressBar)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private ReserveOrderRecycleVIewAdapter<ReserveOrderBean> mAdapter;
    private List<ReserveOrderBean> mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.reserve_order_health_check, container, false);
    }

    @Override
    public ReserveOrderPresenterImpl createPresenter() {
        return new ReserveOrderPresenterImpl();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    protected void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mList = new ArrayList<>();
        getList();
        mAdapter = new ReserveOrderRecycleVIewAdapter<>(0, mList, this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setEmptyView(R.layout.empty_view);
    }

    @Override
    protected void initEvent() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getList();
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ReserveOrderDetailActivity.class);
                intent.putExtra("ReserveOrderBean", mList.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        getList();
    }

    public void OnPackageReady(List<ReserveOrderBean> list) {
        Log.d(TAG, "OnPackageReady: " + list.size());
        List<ReserveOrderBean> list1 = mAdapter.getData();
        list1.clear();
        for (ReserveOrderBean bean : list)
            mAdapter.addData(bean);
        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void NoPackage() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void getList() {
        mPresenter.getHealthCheckedList();
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
    }

    @Override
    public void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }
}
