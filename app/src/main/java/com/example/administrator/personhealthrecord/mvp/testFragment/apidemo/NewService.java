package com.example.administrator.personhealthrecord.mvp.testFragment.apidemo;

import com.example.administrator.personhealthrecord.bean.NewsBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017-7-19.
 */

public interface NewService {

    @GET("information/information_list")
    Observable<List<NewsBean>> getNews();

    @POST("user/login")
    @FormUrlEncoded
    Call<Boolean> login(@Field("username")String username,@Field("password")String password);
}
