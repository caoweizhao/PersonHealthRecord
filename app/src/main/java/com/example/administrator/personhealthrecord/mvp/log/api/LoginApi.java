package com.example.administrator.personhealthrecord.mvp.log.api;

import com.example.administrator.personhealthrecord.bean.Loginbean;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by andy on 2017/7/24.
 */

public interface LoginApi {
    @FormUrlEncoded
    @POST("user/login")
    Observable<Response<Loginbean>> login(@Field("username")String username, @Field("password")String password);
}
