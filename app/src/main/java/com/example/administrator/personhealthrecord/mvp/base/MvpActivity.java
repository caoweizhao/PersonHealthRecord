package com.example.administrator.personhealthrecord.mvp.base;


import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017-7-17.
 */

public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity implements IView<P> {

    public P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attach(MvpActivity.this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detach();
        }
        super.onDestroy();
    }
}
