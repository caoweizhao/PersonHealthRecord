package com.example.administrator.personhealthrecord.mvp.healthevaluate.manual;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.PHRBean;
import com.example.administrator.personhealthrecord.mvp.healthevaluate.HealthyEvaluatePresenter;
import com.example.administrator.personhealthrecord.mvp.healthevaluate.IHealthyEvaluatePresenter;
import com.example.administrator.personhealthrecord.mvp.healthevaluate.IHealthyEvaluateView;

import java.util.zip.Inflater;

/**
 * Created by andy on 2017/8/3.
 */

public class ManualEvaluateFragment extends IHealthyEvaluateView{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.hm_evaluate,container,false);
        return view;
    }

    @Override
    public IHealthyEvaluatePresenter createPresenter() {
        return new HealthyEvaluatePresenter();
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

    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
    }

    @Override
    public void OnPHRReady(PHRBean bean) {

    }
}
