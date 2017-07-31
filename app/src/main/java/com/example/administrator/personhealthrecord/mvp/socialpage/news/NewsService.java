package com.example.administrator.personhealthrecord.mvp.socialpage.news;

import com.example.administrator.personhealthrecord.bean.ResultUtilOfNewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by andy on 2017/7/19.
 */

public interface NewsService {

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
    @GET("information/information_list_today")
    Observable<ResultUtilOfNewsBean> getNewsToday();

    /**
     * 下拉刷新，传入第一条数据的时间戳，获取比该时间戳更新的数据
     *
     * @param time
     * @return
     */
    @GET("information/information_list_after/{time}")
    Observable<ResultUtilOfNewsBean> getLatestNews(@Path("time") long time);

    /**
     * 上拉加载更多，传入最后一条数据的时间戳，获取比该时间戳更旧的数据
     *
     * @param time
     * @return
     */
    @GET("information/information_list_before/{time}")
    Observable<ResultUtilOfNewsBean> getOlderNews(@Path("time") long time);
}
