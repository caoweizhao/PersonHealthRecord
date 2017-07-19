package com.example.administrator.personhealthrecord.mvp.base;


import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017-7-17.
 */

public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment implements IView<P> {

    public P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attach(MvpFragment.this);
        }
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detach();
        }
        super.onDestroy();
    }
}
