package com.example.administrator.personhealthrecord.mvp.homepage.api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017-7-19.
 */

public interface HomePageService {

    @GET("")
    Observable<List<String>> getImagesUrl();

    @GET("")
    Observable<List<String>> getExperts();
}
