package com.example.administrator.personhealthrecord.mvp.testFragment;

import com.example.administrator.personhealthrecord.mvp.base.BasePresenter;

/**
 * Created by Administrator on 2017-7-19.
 */

public class BlankPresenter extends BasePresenter<BlankFragment, BlankModel> {

    @Override
    public BlankModel createModel() {
        return new BlankModel(this);
    }

    public void presenterMethod() {
        mView.showLoading();
        mModel.doGet();
    }

    public void done() {
        mView.dismissLoading();
    }
}
