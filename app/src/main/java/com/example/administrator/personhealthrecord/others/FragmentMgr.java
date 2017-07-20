package com.example.administrator.personhealthrecord.others;

import android.support.v4.app.Fragment;

import com.example.administrator.personhealthrecord.mvp.checkpage.CheckPageFragment;
import com.example.administrator.personhealthrecord.mvp.healthynews.HealthyNewsFragment;
import com.example.administrator.personhealthrecord.mvp.homepage.HomePageFragment;

/**
 * Created by Administrator on 2017-7-20.
 */

public class FragmentMgr {
    public CheckPageFragment mCheckPageFragment;
    public HomePageFragment mHomePageFragment;
    public HealthyNewsFragment mHealthyNewsFragement;

    public static FragmentMgr getInstance() {
        return FragmentMgrHolder.INSTANCE;
    }

    public Fragment getFragment(int pos) {
        if (pos == 0) {
            if (mHomePageFragment == null) {
                mHomePageFragment = HomePageFragment.newInstance();
            }
            return mHomePageFragment;
        }
        if (pos == 2) {
            if (mCheckPageFragment == null) {
                mCheckPageFragment = CheckPageFragment.newInstance();
            }
            return mCheckPageFragment;
        }
        return null;
    }

}

class FragmentMgrHolder {
    static final FragmentMgr INSTANCE = new FragmentMgr();
}