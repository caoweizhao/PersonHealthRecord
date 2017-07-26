package com.example.administrator.personhealthrecord.mvp.healthynews;


import android.util.Log;

import com.example.administrator.personhealthrecord.bean.NewsBean;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfNewsBean;

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
        healthyNewsModle=new HealthyNewsModelImpl();
    }

    @Override//八、返回比时间参数之前的5条数据
    public void getNewsAfter(String string) {
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
                fragment.updateAfterNews(value);
                healthyNewsModle.savaToDatabase(value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e.toString());
                fragment.hidProgressDialog();
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };

        healthyNewsModle.getNewsAfter(observer,string);
    }

    @Override//九、返回今天的资讯数据
    public void getTodayNews() {
        Observer<ResultUtilOfNewsBean> observer=new Observer<ResultUtilOfNewsBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResultUtilOfNewsBean value) {
                if(value.getStatus().equals("success"))
                {
                    List<NewsBean> list=value.getCollection();
                    for(int i=0;i<list.size();i++)
                    {
                        Log.d(TAG, "onNext: "+list.get(i).getImageUrl());
                    }
                    fragment.updateTodayNews(list);
                    healthyNewsModle.savaToDatabase(list);
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e.toString());
                fragment.hidProgressDialog();
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };

        healthyNewsModle.getTodayNews(observer);
    }

    @Override//返回比时间参数之前的5条数据
    public void getNewsBefore(String date) {
        Observer<List<NewsBean>> observer=new Observer<List<NewsBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<NewsBean> value) {
                int i;
                for(i=0;i<value.size();i++)
                {
                    Log.d(TAG, "onNext: "+value.get(i).getTitle());
                }
                fragment.updatebeforeNews(value);
                healthyNewsModle.savaToDatabase(value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e.toString());
                fragment.hidProgressDialog();
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };

        healthyNewsModle.getNewsBefore(observer,date);
    }

    @Override
    public void getDBlist() {
        fragment.updateTodayNews(healthyNewsModle.getDBlist());
    }
}
