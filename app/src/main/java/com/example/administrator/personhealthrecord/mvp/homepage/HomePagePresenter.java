package com.example.administrator.personhealthrecord.mvp.homepage;

import com.example.administrator.personhealthrecord.bean.ExpertBean;

import java.util.List;

/**
 * Created by Administrator on 2017-7-19.
 */

public class HomePagePresenter extends AHomePagePresenter {

    @Override
    public AHomePageModel createModel() {
        // TODO: 2017-7-19
        //return new CheckPageModel(this);
        return new TestModel(this);
    }

    @Override
    public void onImagesReady(List<String> urls) {
        mView.updateImages(urls);
    }

    @Override
    public void onExperssReady(List<ExpertBean> expertBeanList) {
        mView.updateExperts(expertBeanList);
    }

    @Override
    public void onDataReady() {
        mView.dismissLoading();
    }


    @Override
    public void onRequestData() {
        mView.showLoading();
        mModel.getImageRes();
        mModel.getExperts();
    }


}
