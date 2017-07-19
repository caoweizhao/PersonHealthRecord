package com.example.administrator.personhealthrecord.mvp.healthynews;

/**
 * Created by andy on 2017/7/19.
 */

public class HealthyNewsPresenterImpl implements IHealthyNewsPresenter {
    IHealthyNewsFragment fragment;
    public HealthyNewsPresenterImpl(IHealthyNewsFragment fragments)
    {
        this.fragment=fragments;
    }
    @Override
    public void getNewsList(String date) {

    }
}
