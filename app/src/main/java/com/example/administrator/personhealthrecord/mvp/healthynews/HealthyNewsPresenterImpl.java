package com.example.administrator.personhealthrecord.mvp.healthynews;


import android.util.Log;

import com.example.administrator.personhealthrecord.bean.AbstractObjectResult;
import com.example.administrator.personhealthrecord.bean.HealthyNewBean;
import com.example.administrator.personhealthrecord.bean.NewHealthyNewsBean;
import com.example.administrator.personhealthrecord.bean.NewsBean;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfNewsBean;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by andy on 2017/7/19.
 */

public class HealthyNewsPresenterImpl implements IHealthyNewsPresenter {
    private static final String TAG="HealthyNewsPresenter";
    IHealthyNewsFragment fragment;
    HealthyNewsModelImpl healthyNewsModle;
    public HealthyNewsPresenterImpl(IHealthyNewsFragment fragments)
    {
        this.fragment=fragments;
        healthyNewsModle=new HealthyNewsModelImpl();
    }

    @Override//八、返回比时间参数之前的5条数据
    public void getNewsAfter(long date,int page) {
        Observer<AbstractObjectResult<NewHealthyNewsBean>> observer=new Observer<AbstractObjectResult<NewHealthyNewsBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AbstractObjectResult<NewHealthyNewsBean> value) {
                int i;
                for(i=0;i<value.getObject().getContent().size();i++)
                {
                    Log.d(TAG, "onNext: "+value.getObject().getContent().get(i).getImageUrl());
                }
                fragment.updateAfterNews(healthyNewsModle.ConverToN(value.getObject().getContent()));
                healthyNewsModle.savaToDatabase(healthyNewsModle.ConverToN(value.getObject().getContent()));
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

        healthyNewsModle.getNewsAfter(observer,date,page);
    }

    @Override//九、返回今天的资讯数据
    public void getTodayNews() {
        Observer<AbstractObjectResult<NewHealthyNewsBean>> observer=new Observer<AbstractObjectResult<NewHealthyNewsBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AbstractObjectResult<NewHealthyNewsBean> value) {
                if(value.getStatus().equals("success"))
                {
                    List<HealthyNewBean> list=value.getObject().getContent();
                    for(int i=0;i<list.size();i++)
                    {
                        Log.d(TAG, "onNext: "+list.get(i).getImageUrl());
                    }
                    fragment.updateTodayNews(healthyNewsModle.ConverToN(list));
                    healthyNewsModle.savaToDatabase(healthyNewsModle.ConverToN(list));
                }
                Log.d(TAG, "onNext: "+value.getObject().getContent().size());

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

    @Override //返回比时间参数之前的5条数据
    public void getNewsBefore(long date,int page) {
        Observer<AbstractObjectResult<NewHealthyNewsBean>> observer=new Observer<AbstractObjectResult<NewHealthyNewsBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AbstractObjectResult<NewHealthyNewsBean> value) {
                int i;
                for(i=0;i<value.getObject().getContent().size();i++)
                {
                    Log.d(TAG, "onNext: "+value.getObject().getContent().get(i).getTitle());
                }
                fragment.updatebeforeNews(healthyNewsModle.ConverToN(value.getObject().getContent()));
                healthyNewsModle.savaToDatabase(healthyNewsModle.ConverToN(value.getObject().getContent()));
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

        healthyNewsModle.getNewsBefore(observer,date,page);
    }

    @Override
    public void getDBlist() {
        fragment.updateallBDsNews(healthyNewsModle.getDBlist());
    }

    @Override
    public void test() {
        Observer<ResponseBody> observer=new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody value) {
                try {
                    Log.d(TAG, "onNext: "+value.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

        healthyNewsModle.test(observer);
    }
}
