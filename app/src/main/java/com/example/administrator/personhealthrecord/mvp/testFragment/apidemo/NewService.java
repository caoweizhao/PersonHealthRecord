package com.example.administrator.personhealthrecord.mvp.testFragment.apidemo;

import com.example.administrator.personhealthrecord.bean.NewsBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017-7-19.
 */

public interface NewService {

    @GET("information/information_list")
    Observable<List<NewsBean>> getNews();
}
