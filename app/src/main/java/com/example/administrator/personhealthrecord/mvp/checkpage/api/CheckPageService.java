package com.example.administrator.personhealthrecord.mvp.checkpage.api;

import com.example.administrator.personhealthrecord.bean.CheckBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017-7-19.
 */

public interface CheckPageService {

    @GET("")
    Observable<List<String>> getImagesUrl();

    @GET("")
    Observable<List<CheckBean>> getCheckItems();
}
