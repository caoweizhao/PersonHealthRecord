package com.example.administrator.personhealthrecord.mvp.healthynews;


import android.util.Log;

import com.example.administrator.personhealthrecord.bean.NewsBean;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by andy on 2017/7/19.
 */

public class HealthyNewsPresenterImpl implements IHealthyNewsPresenter {
    private static final String TAG="HealthyNewsPresenter";
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
                 int i;
                         for(i=0;i<value.size();i++)
                         {
                             Log.d(TAG, "onNext: "+value.get(i).getImageUrl());
                         }
                         fragment.updateListItem(TestDate.excute());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e.toString());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };

        healthyNewsModle.getNews(observer,date);
    }
}
