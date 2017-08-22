package com.example.administrator.personhealthrecord.mvp.healthynews;

import com.example.administrator.personhealthrecord.bean.AbstractObjectResult;
import com.example.administrator.personhealthrecord.bean.NewsBean;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfNewsBean;
import com.example.administrator.personhealthrecord.bean.NewHealthyNewsBean;

import java.util.List;

import io.reactivex.Observer;
import okhttp3.ResponseBody;


/**
 * Created by andy on 2017/7/19.
 */

public interface IHealthyNewsModle {
    void getNewsAfter(Observer<AbstractObjectResult<NewHealthyNewsBean>> observer, long date,int page);
    void getTodayNews(Observer<AbstractObjectResult<NewHealthyNewsBean>> observer);
    void getNewsBefore(Observer<AbstractObjectResult<NewHealthyNewsBean>> observer, long date,int page);
    void savaToDatabase(List<NewsBean> list);
    List<NewsBean> getDBlist();
    void test(Observer<ResponseBody> observer);
}
