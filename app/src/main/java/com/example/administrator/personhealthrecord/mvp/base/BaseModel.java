package com.example.administrator.personhealthrecord.mvp.base;

/**
 * Created by Administrator on 2017-7-17.
 */

public abstract class BaseModel<P extends BasePresenter> {

    public P mPresenter;

    public BaseModel(P presenter) {
        this.mPresenter = presenter;
    }
}
