package com.example.administrator.personhealthrecord.mvp.healthynews;

import com.example.administrator.personhealthrecord.bean.AbstractObjectResult;
import com.example.administrator.personhealthrecord.bean.NewHealthyNewsBean;
import com.example.administrator.personhealthrecord.bean.NewsBean;

import java.util.List;

import io.reactivex.Observer;
import okhttp3.ResponseBody;


/**
 * Created by andy on 2017/7/19.
 */

interface IHealthyNewsModel {
    void getNewsAfter(Observer<AbstractObjectResult<NewHealthyNewsBean>> observer, long date, int page);

    void getTodayNews(Observer<AbstractObjectResult<NewHealthyNewsBean>> observer);

    void getNewsBefore(Observer<AbstractObjectResult<NewHealthyNewsBean>> observer, long date, int page);

    void saveToDatabase(List<NewsBean> list);

    List<NewsBean> getDBList();

    void test(Observer<ResponseBody> observer);
}
