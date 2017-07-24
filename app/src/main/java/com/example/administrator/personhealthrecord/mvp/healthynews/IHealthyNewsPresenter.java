package com.example.administrator.personhealthrecord.mvp.healthynews;

import com.example.administrator.personhealthrecord.bean.NewsBean;

import java.util.List;

import io.reactivex.Observer;

/**
 * Created by andy on 2017/7/19.
 */

public interface IHealthyNewsPresenter {
    public void getNewsAfter(String string);
    public void getTodayNews();
    public void getNewsBefore(String date);
    public void getDBlist();
}
