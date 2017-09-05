package com.example.administrator.personhealthrecord.mvp.socialpage.news;

import com.example.administrator.personhealthrecord.bean.ResultUtilOfNewsBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * Created by andy on 2017/7/19.
 */

interface NewsService {

    /**
     * 第一次进入页面，加载数据库信息，加载当天信息
     * 下拉刷新，获取当前第一条之前信息
     * 上拉加载更多，获取最后一条之前的信息
     */

    /**
     * 第一次进入页面，加载数据库信息，并且同时加载数据库
     *
     * @return
     */
    @FormUrlEncoded
    @POST("information/information_list_today")
    Observable<ResultUtilOfNewsBean> getNewsToday(@Field("page") int page, @Field("size") int size);

    /**
     * 下拉刷新，传入第一条数据的时间戳，获取比该时间戳更新的数据
     *
     * @return
     */
    @FormUrlEncoded
    @POST("information/information_list_after")
    Observable<ResultUtilOfNewsBean> getLatestNews(@Field("time") long timeStamp,
                                                   @Field("page") int page,
                                                   @Field("size") int size);

    /**
     * 上拉加载更多，传入最后一条数据的时间戳，获取比该时间戳更旧的数据
     *
     * @return
     */
    @FormUrlEncoded
    @POST("information/information_list_before")
    Observable<ResultUtilOfNewsBean> getOlderNews(@Field("time") long timeStamp,
                                                  @Field("page") int page,
                                                  @Field("size") int size);
}
