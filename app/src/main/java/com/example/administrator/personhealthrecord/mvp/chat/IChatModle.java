package com.example.administrator.personhealthrecord.mvp.chat;

import com.example.administrator.personhealthrecord.bean.AIResponeBean;
import com.example.administrator.personhealthrecord.bean.AIResponeHelpBean;
import com.example.administrator.personhealthrecord.mvp.base.BaseModel;

import io.reactivex.Observer;

/**
 * Created by andy on 2017/7/24.
 */

public abstract class IChatModle extends BaseModel<IChatPresenter>{
    public IChatModle(IChatPresenter presenter) {
        super(presenter);
    }
    abstract void getHelper(Observer<AIResponeHelpBean> observer);
    abstract void getRespone(Observer<AIResponeBean> observer, String question);
}
