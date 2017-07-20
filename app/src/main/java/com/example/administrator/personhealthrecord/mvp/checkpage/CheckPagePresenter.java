package com.example.administrator.personhealthrecord.mvp.checkpage;

import com.example.administrator.personhealthrecord.bean.CheckBean;

import java.util.List;

/**
 * Created by Administrator on 2017-7-19.
 */

public class CheckPagePresenter extends ACheckPagePresenter {

    @Override
    public ACheckPageModel createModel() {
        // TODO: 2017-7-19
        //return new CheckPageModel(this);
        return new TestModel(this);
    }

    @Override
    public void onImagesReady(List<String> urls) {
        mView.updateImages(urls);
    }

    @Override
    public void onDataReady(List<CheckBean> checkBeanList) {
        mView.dismissLoading();
        mView.updateCheckItems(checkBeanList);
    }


    @Override
    public void onRequestData() {
        mView.showLoading();
        mModel.getImageRes();
        mModel.getCheckItems();
    }


}
