package com.example.administrator.personhealthrecord.mvp.healthynews;

import com.example.administrator.personhealthrecord.bean.HealthInfo;
import com.example.administrator.personhealthrecord.bean.NewsBean;


import org.reactivestreams.Subscriber;

import java.util.List;

import io.reactivex.Observer;


/**
 * Created by andy on 2017/7/19.
 */

public interface IHealthyNewsModle {
    public void getNews(Observer<List<NewsBean>> observer, String string);
}
