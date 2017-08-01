package com.example.administrator.personhealthrecord.mvp.reserveorder.healthcheckorder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.mvp.base.MvpFragment;
import com.example.administrator.personhealthrecord.mvp.reserveorder.IResreveOrderPresenter;
import com.example.administrator.personhealthrecord.mvp.reserveorder.ReserveOrderPresenterImpl;

import butterknife.BindView;


/**
 * Created by andy on 2017/7/31.
 */

public class HealthyCheckOrderFragment extends MvpFragment<IResreveOrderPresenter>{
    @BindView(R.id.health_check_reserve_order_ProgressBar)
    SwipeRefreshLayout swipeRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.reserve_order_health_check,container,false);
        return view;
    }

    @Override
    public IResreveOrderPresenter createPresenter() {
        return new ReserveOrderPresenterImpl();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        mPresenter.getHealthCheckeList();
    }
}
