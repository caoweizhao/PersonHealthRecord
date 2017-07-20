package com.example.administrator.personhealthrecord.mvp.homepage;

import com.example.administrator.personhealthrecord.bean.ExpertBean;
import com.example.administrator.personhealthrecord.mvp.base.BasePresenter;

import java.util.List;

/**
 * Created by Administrator on 2017-7-19.
 */

public abstract class AHomePagePresenter extends BasePresenter<AHomePageFragment, AHomePageModel> {

    public abstract void onImagesReady(List<String> urls);

    public abstract void onExperssReady(List<ExpertBean> expertBeanList);

    public abstract void onDataReady();

    public abstract void onRequestData();

}
