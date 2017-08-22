package com.example.administrator.personhealthrecord.mvp.chat;

import com.example.administrator.personhealthrecord.mvp.base.BasePresenter;


/**
 * Created by andy on 2017/7/24.
 */

abstract class IChatPresenter extends BasePresenter<IChatVIew,IChatModel>{
    abstract void getHelp();
    abstract void getResponse(String question);
}
