package com.example.administrator.personhealthrecord.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.mvp.base.BaseActivity;

/**
 * Created by Administrator on 2017-7-26.
 */

public class TestActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.add_case;
    }
}
