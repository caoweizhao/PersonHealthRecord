package com.example.administrator.personhealthrecord.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.util.AnimateUtil;

import butterknife.BindView;

/**
 * Created by Administrator on 2017-8-5.
 */

public class AboutActivity extends BaseActivity {
    @BindView(R.id.about_activity_container)
    View mContainer;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_about_layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sm.setStatusBarTintEnabled(false);
        AnimateUtil.scaleShow(mContainer,null);
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        mContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                finish();
                return true;
            }
        });
    }
}
