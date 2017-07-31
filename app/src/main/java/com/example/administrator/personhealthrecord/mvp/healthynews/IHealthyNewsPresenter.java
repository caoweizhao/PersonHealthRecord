package com.example.administrator.personhealthrecord.mvp.healthynews;

/**
 * Created by andy on 2017/7/19.
 */

public interface IHealthyNewsPresenter {
    public void getNewsAfter(long string);
    public void getTodayNews();
    public void getNewsBefore(long date);
    public void getDBlist();
}
