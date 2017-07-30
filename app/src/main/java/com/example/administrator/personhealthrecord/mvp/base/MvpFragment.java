package com.example.administrator.personhealthrecord.mvp.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;

import com.example.administrator.personhealthrecord.base.BaseFragment;
import com.example.administrator.personhealthrecord.mvp.main.MainActivity;

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

    Snackbar mSnackbar;

    public void showMessage(String message) {
        if (mSnackbar == null) {
            mSnackbar = Snackbar.make(((MainActivity) getActivity()).mDrawerLayout, message, Snackbar.LENGTH_SHORT);
        } else {
            mSnackbar.setText(message);
        }
        mSnackbar.show();
    }
}
