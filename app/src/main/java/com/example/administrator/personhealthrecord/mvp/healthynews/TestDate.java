package com.example.administrator.personhealthrecord.mvp.healthynews;

import com.example.administrator.personhealthrecord.bean.NewsBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by andy on 2017/7/20.
 */

public class TestDate {
    public static List<NewsBean> excute()
    {
        List<NewsBean> list=new ArrayList<>();
        for(int i=0;i<8;i++)
        {
            NewsBean bean=new NewsBean();
            bean.setTime(1111);
            bean.setTitle("新闻"+i);
            bean.setSummary("新闻详情"+i);
            bean.setImageUrl("http://www.toutiaoba.com/template/comiis_mi/img/comiis_mini_logo.png");
            list.add(bean);
        }
        return list;
    }
}
