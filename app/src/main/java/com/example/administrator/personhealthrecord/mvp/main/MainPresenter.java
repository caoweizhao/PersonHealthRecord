package com.example.administrator.personhealthrecord.mvp.main;

import android.support.annotation.IdRes;

import com.example.administrator.personhealthrecord.R;

/**
 * Created by Administrator on 2017-7-17.
 */

public class MainPresenter extends AMainPresenter {

    @Override
    public AMainModel createModel() {
        return new MainModel(this);
    }

    @Override
    public void onTabSelected(@IdRes int id) {
        int index = 0;
        switch (id) {
            case R.id.tab_home_page:
                index = 0;
                break;
            case R.id.tab_community:
                index = 1;
                break;
            case R.id.tab_health:
                index = 2;
                break;
            case R.id.tab_physical_examination:
                index = 3;
                break;
            default:
                break;
        }
        mView.setFragment(index);
    }
}
