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
    @GET("health_info/health_info_list_today")
    Observable<ResultUtilOfNewsBean> getNewsToday();

    @GET("health_info/health_info_list_after/{time}")
    Observable<ResultUtilOfNewsBean> getNewsAfter(@Path("time") long time);


    @GET("health_info/health_info_list_before/{time}")
    Observable<ResultUtilOfNewsBean> getNewsBefore(@Path("time") long time);
}
