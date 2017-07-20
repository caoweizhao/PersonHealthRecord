package com.example.administrator.personhealthrecord.mvp.healthynews.api;

import com.example.administrator.personhealthrecord.bean.NewsBean;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.GET;


/**
 * Created by andy on 2017/7/19.
 */

public interface HealthyNewsApi {
    @GET("information/information_list")
    Observable<List<NewsBean>> getNews();
}
