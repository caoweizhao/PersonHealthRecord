package com.example.administrator.personhealthrecord.mvp.main;


import android.support.annotation.IdRes;

import com.example.administrator.personhealthrecord.mvp.base.BasePresenter;

/**
 * Created by Administrator on 2017-7-18.
 */

public abstract class AMainPresenter extends BasePresenter<AMainActivity, AMainModel> {


    public abstract void onTabSelected(@IdRes int id);

    public abstract void requestAvator();

    public abstract void onAvatorUrlReady(String url);
}
