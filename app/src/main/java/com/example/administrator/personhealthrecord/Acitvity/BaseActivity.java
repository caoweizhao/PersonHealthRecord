package com.example.administrator.personhealthrecord.Acitvity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.example.administrator.personhealthrecord.Application.MyApplication;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by andy on 2017/7/18.
 */

public class BaseActivity extends Activity{
    protected final String TAG = getClass().getSimpleName();
    public static BaseActivity activity;
    public Unbinder unbinder;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        // TODO: 16/9/1  add the third service. eg.umeng ...
         unbinder=ButterKnife.bind(this);
        activity = this;
        ((MyApplication) MyApplication.getContext()).addActivity(this);
        init();

    }

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
        super.onDestroy();
        unbinder.unbind();
        ((MyApplication) MyApplication.getContext()).removeActivity(this);
    }
}
