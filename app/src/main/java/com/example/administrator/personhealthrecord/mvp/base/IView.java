package com.example.administrator.personhealthrecord.mvp.base;

/**
 * Created by Administrator on 2017-7-17.
 */

interface IView<P> {
     P createPresenter();

     void showLoading();
     void dismissLoading();
}