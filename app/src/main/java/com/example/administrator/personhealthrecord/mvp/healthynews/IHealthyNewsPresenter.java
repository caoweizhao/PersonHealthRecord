package com.example.administrator.personhealthrecord.mvp.healthynews;

/**
 * Created by andy on 2017/7/19.
 */

public interface IHealthyNewsPresenter {
    public void getNewsAfter(String string);
    public void getTodayNews();
    public void getNewsBefore(String date);
    public void getDBlist();
}
