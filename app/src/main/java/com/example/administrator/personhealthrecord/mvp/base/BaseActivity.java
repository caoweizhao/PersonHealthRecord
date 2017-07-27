package com.example.administrator.personhealthrecord.mvp.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.application.MyApplication;
import com.example.administrator.personhealthrecord.others.FragmentMgr;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.Unbinder;

import static butterknife.ButterKnife.bind;


/**
 * Created by andy on 2017/7/18.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected final String TAG = getClass().getSimpleName();
    public static BaseActivity activity;
    private Unbinder mUnbinder;
    public FragmentMgr fragmentMgr;
    protected SystemBarTintManager sm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        ((MyApplication) MyApplication.getContext()).addActivity(this);
        setContentView(getLayoutRes());
        init();
        sm = new SystemBarTintManager(this);
        sm.setStatusBarTintEnabled(true);
        sm.setStatusBarTintColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mUnbinder = bind(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        mUnbinder = bind(this);
    }

    protected abstract int getLayoutRes();

    @Override
    protected void onResume() {
        super.onResume();
        activity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        activity = null;

    }

    private void init() {
        initData();
        initEvents();
    }

    /***
     * 初始化事件（监听事件等事件绑定）
     */
    protected void initEvents() {
    }

    /**
     * 绑定数据
     */
    protected void initData() {
    }

    /**
     * activity退出时将activity移出栈
     */
    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
        ((MyApplication) MyApplication.getContext()).removeActivity(this);
    }

}
