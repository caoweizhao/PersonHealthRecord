package com.example.administrator.personhealthrecord.mvp.main;


import com.example.administrator.personhealthrecord.mvp.base.MvpActivity;

/**
 * Created by Administrator on 2017-7-17.
 */

public abstract class AMainActivity extends MvpActivity<AMainPresenter> {
    //定义main里关于view的方法

    public abstract void mainViewMethod();
}
