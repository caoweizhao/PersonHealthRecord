package com.example.administrator.personhealthrecord.mvp.registandlogin.api;

import com.example.administrator.personhealthrecord.bean.Loginbean;
import com.example.administrator.personhealthrecord.bean.RegistBean;

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
    Observable<Response<Loginbean>> login(@Field("username")String username, @Field("password")String password);

    @FormUrlEncoded
    @POST("user/register")
    Observable<RegistBean> regist(@Field("username")String username, @Field("password")String password);

    @PUT("/user/logout")
    Observable<ResponseBody> loginOut(@Header("Cookie")String cookie);
}
