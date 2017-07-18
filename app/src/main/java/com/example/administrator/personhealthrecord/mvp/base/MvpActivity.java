package com.example.administrator.personhealthrecord.mvp.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2017-7-17.
 */

public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity implements IView<P> {

    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            Log.d("MvpActivity",MvpActivity.this.toString());
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
