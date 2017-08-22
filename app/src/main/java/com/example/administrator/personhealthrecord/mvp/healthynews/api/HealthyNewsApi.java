package com.example.administrator.personhealthrecord.mvp.healthynews.api;

import android.util.Size;

import com.example.administrator.personhealthrecord.bean.AbstractObjectResult;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfNewsBean;
import com.example.administrator.personhealthrecord.bean.NewHealthyNewsBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by andy on 2017/7/19.
 */

public interface HealthyNewsApi {
    @FormUrlEncoded
    @POST("health_info/health_info_list_today")
    Observable<AbstractObjectResult<NewHealthyNewsBean>> getNewsToday(@Field("page")int page,@Field("size") int size);

    @FormUrlEncoded
    @POST("health_info/health_info_list_after")
    Observable<AbstractObjectResult<NewHealthyNewsBean>> getNewsAfter(@Field("time") long time,@Field("page")int page,@Field("size")int size);

    @FormUrlEncoded
    @POST("health_info/health_info_list_before")
    Observable<AbstractObjectResult<NewHealthyNewsBean>> getNewsBefore(@Field("time") long time,@Field("page")int page,@Field("size")int size);

    @GET("/user/test")
    Observable<ResponseBody> test();
}
