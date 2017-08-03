package com.example.administrator.personhealthrecord.mvp.healthynews;

import com.example.administrator.personhealthrecord.bean.NewsBean;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfNewsBean;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.ResponseBody;
import retrofit2.Response;


/**
 * Created by andy on 2017/7/19.
 */

public interface IHealthyNewsModle {
    public void getNewsAfter(Observer<ResultUtilOfNewsBean> observer, long date);
    public void getTodayNews(Observer<ResultUtilOfNewsBean> observer);
    public void getNewsBefore(Observer<ResultUtilOfNewsBean> observer,long date);
    public void savaToDatabase(List<NewsBean> list);
    public List<NewsBean> getDBlist();
    public void test(Observer<ResponseBody> observer);
}
