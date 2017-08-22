package com.example.administrator.personhealthrecord.mvp.chat;

import com.example.administrator.personhealthrecord.bean.AIResponseBean;
import com.example.administrator.personhealthrecord.bean.AIResponeHelpBean;
import com.example.administrator.personhealthrecord.mvp.base.BaseModel;

import io.reactivex.Observer;

/**
 * Created by andy on 2017/7/24.
 */

abstract class IChatModel extends BaseModel<IChatPresenter> {
    IChatModel(IChatPresenter presenter) {
        super(presenter);
    }

    abstract void getHelper(Observer<AIResponeHelpBean> observer);

    abstract void getResponse(Observer<AIResponseBean> observer, String question);
}
