package com.example.administrator.personhealthrecord.mvp.healthynews;

import android.util.Log;

import com.example.administrator.personhealthrecord.bean.NewsBean;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfNewsBean;
import com.example.administrator.personhealthrecord.mvp.healthynews.api.HealthyNewsApi;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;

import org.litepal.crud.DataSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;



/**
 * Created by andy on 2017/7/19.
 */

public class HealthyNewsModleImpl implements IHealthyNewsModle{
    private static final String TAG="HealthyNewsModleImpl";
    private Retrofit retrofit;
    @Override
    public void getNewsAfter(Observer<List<NewsBean>> observer, String date) {
        retrofit= RetrofitUtil.getRetrofit();
        HealthyNewsApi api=retrofit.create(HealthyNewsApi.class);
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1=format.parse(date);

        api.getNewsAfter(date1.getTime())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getTodayNews(Observer<ResultUtilOfNewsBean> observer) {
        retrofit= RetrofitUtil.getRetrofit();
        HealthyNewsApi api=retrofit.create(HealthyNewsApi.class);
        api.getNewsToday()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getNewsBefore(Observer<List<NewsBean>> observer, String date) {
        retrofit= RetrofitUtil.getRetrofit();
        HealthyNewsApi api=retrofit.create(HealthyNewsApi.class);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1=format.parse(date);

            api.getNewsBefore(date1.getTime())
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void savaToDatabase(List<NewsBean> list) {
        List<NewsBean> dblist= DataSupport.findAll(NewsBean.class);
        for(NewsBean bean:list)
        {
            if(!dblist.contains(bean))
                bean.save();
        }
    }

    @Override
    public List<NewsBean> getDBlist() {
        List<NewsBean> list=DataSupport.findAll(NewsBean.class);
        for (NewsBean bean: list)
            Log.d(TAG, "getDBlist: "+bean.getTitle());
        Collections.sort(list);
        return list;
    }
}
