package com.example.administrator.personhealthrecord.mvp.healthynews;

import com.example.administrator.personhealthrecord.bean.NewsBean;

import java.util.List;

/**
 * Created by andy on 2017/7/19.
 */

public interface IHealthyNewsFragment {
    void showProgressDialog();

    void hidProgressDialog();

    void updateAfterNews(List<NewsBean> list);

    void updateTodayNews(List<NewsBean> list);

    void updatebeforeNews(List<NewsBean> list);

    void updateallBDsNews(List<NewsBean> list);
}
