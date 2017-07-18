package com.example.administrator.personhealthrecord.mvp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2017-7-17.
 */

public class MainActivity extends AMainActivity {

    @Override
    public AMainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void showLoading() {
        Log.d("MainActivity", "showLoading");
    }

    @Override
    public void dismissLoading() {
        Log.d("MainActivity", "dismissLoading");
    }

    @Override
    public void mainViewMethod() {
        Log.d("MainActivity", "showLoading");
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity","onCreate");
        mPresenter.mainPresenterMethod();
    }
}
