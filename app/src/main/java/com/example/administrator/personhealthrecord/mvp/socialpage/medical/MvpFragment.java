package com.example.administrator.personhealthrecord.mvp.socialpage.medical;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.administrator.personhealthrecord.mvp.base.BasePresenter;
import com.example.administrator.personhealthrecord.mvp.base.IView;

/**
 * Created by Administrator on 2017-7-17.
 */

public abstract class MvpFragment<P extends BasePresenter> extends Fragment implements IView<P> {

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
