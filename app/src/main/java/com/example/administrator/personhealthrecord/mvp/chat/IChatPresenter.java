package com.example.administrator.personhealthrecord.mvp.chat;

import com.example.administrator.personhealthrecord.mvp.base.BasePresenter;


/**
 * Created by andy on 2017/7/24.
 */

public abstract class IChatPresenter extends BasePresenter<IChatVIew,IChatModle>{
    abstract void getHelpe();
    abstract void getResone(String question);
}
