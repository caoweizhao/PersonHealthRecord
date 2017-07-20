package com.example.administrator.personhealthrecord.mvp.healthynews;

import com.example.administrator.personhealthrecord.bean.HealthInfo;
import com.example.administrator.personhealthrecord.bean.NewsBean;
import com.example.administrator.personhealthrecord.mvp.base.BaseModel;
import com.example.administrator.personhealthrecord.mvp.base.BasePresenter;
import com.example.administrator.personhealthrecord.mvp.healthynews.api.HealthyNewsApi;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;

import org.reactivestreams.Subscriber;

import java.util.List;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;



/**
 * Created by andy on 2017/7/19.
 */

public class HealthyNewsModleImpl implements IHealthyNewsModle{
    private Retrofit retrofit;
    @Override
    public void getNews(Observer<List<NewsBean>> observer, String date) {
        retrofit= RetrofitUtil.getRetrofit();
        HealthyNewsApi api=retrofit.create(HealthyNewsApi.class);
        api.getNews()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
