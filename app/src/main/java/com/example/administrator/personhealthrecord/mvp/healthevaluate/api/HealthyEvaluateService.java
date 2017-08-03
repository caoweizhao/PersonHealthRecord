package com.example.administrator.personhealthrecord.mvp.healthevaluate.api;

import io.reactivex.Observer;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by andy on 2017/8/3.
 */

public interface HealthyEvaluateService {
    @GET("/phr/search")
    Observer<ResponseBody> getPHRdata(@Header("Cookie")String cookie);
}
