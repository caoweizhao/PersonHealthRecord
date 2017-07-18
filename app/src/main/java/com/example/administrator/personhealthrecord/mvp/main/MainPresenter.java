package com.example.administrator.personhealthrecord.mvp.main;

import android.util.Log;

/**
 * Created by Administrator on 2017-7-17.
 */

public class MainPresenter extends AMainPresenter {

    @Override
    public void mainPresenterMethod() {
        Log.d("MainPresenter","mainPresenterMethod");

        mView.showLoading();
        mView.mainViewMethod();
        mModel.mainModelMethod();
    }

    @Override
    public void mainPresenterMethod2() {
        Log.d("MainPresenter","mainPresenterMethod2");
        mView.dismissLoading();
    }

    @Override
    public AMainModel createModel() {
        return new MainModel(this);
    }
}
