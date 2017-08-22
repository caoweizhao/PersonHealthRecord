package com.example.administrator.personhealthrecord.mvp.main;


import com.example.administrator.personhealthrecord.mvp.base.BaseModel;

/**
 * Created by Administrator on 2017-7-17.
 */

abstract class AMainModel extends BaseModel<AMainPresenter> {

    AMainModel(AMainPresenter presenter) {
        super(presenter);
    }

    public abstract void getAvatorUrl();
}
