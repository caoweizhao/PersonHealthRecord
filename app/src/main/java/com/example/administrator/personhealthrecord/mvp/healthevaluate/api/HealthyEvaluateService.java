package com.example.administrator.personhealthrecord.mvp.healthevaluate.api;

import com.example.administrator.personhealthrecord.bean.AbstractObjectResult;
import com.example.administrator.personhealthrecord.bean.PHRBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by andy on 2017/8/3.
 */

public interface HealthyEvaluateService {
    @GET("/phr/search")
    Observable<AbstractObjectResult<PHRBean>> getPHRdata(@Header("Cookie")String cookie);

    @POST("phr/count_phr_mark")
   Observable<AbstractObjectResult<Integer>> getPHR(@Header("Cookie")String cookie, @Body PHRBean bean);
}