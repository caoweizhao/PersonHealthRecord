package com.example.administrator.personhealthrecord.mvp.main;


import android.util.Log;

/**
 * Created by Administrator on 2017-7-17.
 */

public class MainModel extends AMainModel {

    public MainModel(AMainPresenter presenter) {
        super(presenter);
    }

    @Override
    public void mainModelMethod() {
        Log.d("MainModel","mainModelMethod");
        mPresenter.mainPresenterMethod2();
    }
}