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
    void getNewsAfter(Observer<ResultUtilOfNewsBean> observer, long date);
    void getTodayNews(Observer<ResultUtilOfNewsBean> observer);
    void getNewsBefore(Observer<ResultUtilOfNewsBean> observer, long date);
    void savaToDatabase(List<NewsBean> list);
    List<NewsBean> getDBlist();
    void test(Observer<ResponseBody> observer);
}
