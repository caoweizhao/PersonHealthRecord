package com.example.administrator.personhealthrecord.mvp.register_and_login.api;

import com.example.administrator.personhealthrecord.bean.LoginBean;
import com.example.administrator.personhealthrecord.bean.RegisterBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by andy on 2017/7/24.
 */

public interface LoginApi {
    @FormUrlEncoded
    @POST("user/login")
    Observable<Response<LoginBean>> login(@Field("username")String username, @Field("password")String password);

    @FormUrlEncoded
    @POST("user/register")
    Observable<RegisterBean> register(@Field("username")String username, @Field("password")String password);

    @PUT("/user/logout")
    Observable<ResponseBody> loginOut(@Header("Cookie")String cookie);
}
