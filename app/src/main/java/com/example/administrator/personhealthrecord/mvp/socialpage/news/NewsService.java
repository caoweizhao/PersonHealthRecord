package com.example.administrator.personhealthrecord.mvp.healthynews.api;

import com.example.administrator.personhealthrecord.bean.NewsBean;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfNewsBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by andy on 2017/7/19.
 */

public interface HealthyNewsApi {
    @GET("information/information_list_today")
    Observable<ResultUtilOfNewsBean> getNewsToday();


    @GET("information/information_list_today")
    Observable<List<NewsBean>> getNewsAfter(@Query("date") long time);


    @GET("information/information_list_before/{date}")
    Observable<List<NewsBean>> getNewsBefore(@Path("date") long time);
}
