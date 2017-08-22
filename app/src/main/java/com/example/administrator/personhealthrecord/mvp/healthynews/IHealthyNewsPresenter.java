package com.example.administrator.personhealthrecord.mvp.healthynews;

/**
 * Created by andy on 2017/7/19.
 */

public interface IHealthyNewsPresenter {
    void getNewsAfter(long string,int page);
    void getTodayNews();
    void getNewsBefore(long date,int page);
    void getDBlist();
    void test();
}
