package com.example.administrator.personhealthrecord.mvp.healthynews;

import android.os.Build;
import android.util.Log;
import android.webkit.WebSettings;

import com.example.administrator.personhealthrecord.application.MyApplication;
import com.example.administrator.personhealthrecord.bean.HealthyNewBean;
import com.example.administrator.personhealthrecord.bean.NewsBean;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfNewsBean;
import com.example.administrator.personhealthrecord.mvp.healthynews.api.HealthyNewsApi;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;

import org.litepal.crud.DataSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * Created by andy on 2017/7/19.
 */

public class HealthyNewsModelImpl implements IHealthyNewsModle {
    private static final String TAG = "HealthyNewsModelImpl";
    private Retrofit retrofit;

    @Override
    public void getNewsAfter(Observer<ResultUtilOfNewsBean> observer, long date) {
        retrofit = RetrofitUtil.getRetrofit();
        HealthyNewsApi api = retrofit.create(HealthyNewsApi.class);
        api.getNewsAfter(date)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getTodayNews(Observer<ResultUtilOfNewsBean> observer) {
        retrofit = RetrofitUtil.getRetrofit();
        HealthyNewsApi api = retrofit.create(HealthyNewsApi.class);
        api.getNewsToday()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getNewsBefore(Observer<ResultUtilOfNewsBean> observer, long date) {
        retrofit = RetrofitUtil.getRetrofit();
        HealthyNewsApi api = retrofit.create(HealthyNewsApi.class);


        api.getNewsBefore(date)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    @Override
    public void savaToDatabase(List<NewsBean> list) {
        List<HealthyNewBean> dblist = DataSupport.findAll(HealthyNewBean.class);
        List<HealthyNewBean> Mylist = ConverToH(list);
        for (HealthyNewBean bean : Mylist) {
            if (!dblist.contains(bean))
                bean.save();
        }
    }

    @Override
    public List<NewsBean> getDBlist() {
        List<HealthyNewBean> list = DataSupport.findAll(HealthyNewBean.class);
        for (HealthyNewBean bean : list)
            Log.d(TAG, "getDBlist: " + bean.getTitle());
        Collections.sort(list);

        return ConverToN(list);
    }


    @Override
    public void test(Observer<ResponseBody> observer) {

        retrofit = RetrofitUtil.getRetrofit();
        HealthyNewsApi api = retrofit.create(HealthyNewsApi.class);
        api.test()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public List<HealthyNewBean> ConverToH(List<NewsBean> list) {
        List<HealthyNewBean> Hlist = new ArrayList<>();
        for (NewsBean bean : list) {
            HealthyNewBean Hbean = new HealthyNewBean(bean.getId(), bean.getTitle(), bean.getSummary(), bean.getContent(), bean.getCategory(), bean.getTime(), bean.getImageUrl());
            Hlist.add(Hbean);
        }
        return Hlist;
    }

    public List<NewsBean> ConverToN(List<HealthyNewBean> list) {
        List<NewsBean> Hlist = new ArrayList<>();
        for (HealthyNewBean bean : list) {
            NewsBean Hbean = new NewsBean(bean.getId(), bean.getTitle(), bean.getSummary(), bean.getContent(), bean.getCategory(), bean.getTime(), bean.getImageUrl());
            Hlist.add(Hbean);
        }
        return Hlist;
    }
}
