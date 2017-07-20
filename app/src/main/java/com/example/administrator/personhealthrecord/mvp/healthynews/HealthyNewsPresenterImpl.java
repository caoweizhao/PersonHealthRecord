package com.example.administrator.personhealthrecord.mvp.healthynews;


import com.example.administrator.personhealthrecord.bean.NewsBean;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by andy on 2017/7/19.
 */

public class HealthyNewsPresenterImpl implements IHealthyNewsPresenter {
    IHealthyNewsFragment fragment;
    IHealthyNewsModle healthyNewsModle;
    public HealthyNewsPresenterImpl(IHealthyNewsFragment fragments)
    {
        this.fragment=fragments;
        healthyNewsModle=new HealthyNewsModleImpl();
    }
    @Override
    public void getNewsList(String date) {
        Observer<List<NewsBean>> observer=new Observer<List<NewsBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<NewsBean> value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        healthyNewsModle.getNews(observer,date);
    }
}
