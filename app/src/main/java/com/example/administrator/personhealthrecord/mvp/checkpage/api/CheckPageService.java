package com.example.administrator.personhealthrecord.mvp.checkpage.api;

import com.example.administrator.personhealthrecord.bean.AbstractResultUtil;
import com.example.administrator.personhealthrecord.bean.CheckBean;
import com.example.administrator.personhealthrecord.bean.ImageBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017-7-19.
 */

public interface CheckPageService {

    @GET("advertisement/medical_ad_list")
    Observable<AbstractResultUtil<ImageBean>> getImagesUrl();

    @GET("")
    Observable<List<CheckBean>> getCheckItems();
}
