package com.example.administrator.personhealthrecord.mvp.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import com.example.administrator.personhealthrecord.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;

/**
 * Created by Administrator on 2017-7-17.
 */

public class MainActivity extends AMainActivity {

    @BindView(R.id.main_container)
    FrameLayout mFrameLayout;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    protected void initEvents() {
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                mPresenter.onTabSelected(tabId);
            }
        });
    }

    @Override
    public AMainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void dismissLoading() {
    }

    @Override
    public void setFragment(int position) {
        switch(position)
            {
                case 1:

                    break;
                default:

                    break;
            }
    }

}
