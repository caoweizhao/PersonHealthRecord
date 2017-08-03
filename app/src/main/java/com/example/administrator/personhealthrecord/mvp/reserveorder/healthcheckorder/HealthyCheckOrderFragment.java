package com.example.administrator.personhealthrecord.mvp.reserveorder.healthcheckorder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.adapter.ReserveOrderRecycleVIewAdapter;
import com.example.administrator.personhealthrecord.bean.ReserOrderBean;
import com.example.administrator.personhealthrecord.mvp.base.MvpFragment;
import com.example.administrator.personhealthrecord.mvp.reserveorder.IReserveOrderView;
import com.example.administrator.personhealthrecord.mvp.reserveorder.IResreveOrderPresenter;
import com.example.administrator.personhealthrecord.mvp.reserveorder.ReserveOrderPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by andy on 2017/7/31.
 */

public class HealthyCheckOrderFragment extends IReserveOrderView{
    private static final String TAG="HealthyCheckOrder";
    @BindView(R.id.reserve_order_recycleview)
    RecyclerView recycleview;
    @BindView(R.id.health_check_reserve_order_ProgressBar)
    SwipeRefreshLayout swipeRefreshLayout;
    private ReserveOrderRecycleVIewAdapter<ReserOrderBean> adapter;
    private List<ReserOrderBean> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.reserve_order_health_check,container,false);
        return view;
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
        recycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        list=new ArrayList<>();
        getList();
        adapter=new ReserveOrderRecycleVIewAdapter<ReserOrderBean>(0,list,this);
        recycleview.setAdapter(adapter);
    }
    @Override
    protected void initEvent() {

    }
    public void OnPackageReady(List<ReserOrderBean> list)
    {
        Log.d(TAG, "OnPackageReady: "+list.size());
        List<ReserOrderBean> list1=adapter.getData();
        for(ReserOrderBean bean:list)
            if(!list1.contains(bean))
                adapter.addData(bean);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getList() {
        mPresenter.getHealthCheckeList();
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
    }
}
