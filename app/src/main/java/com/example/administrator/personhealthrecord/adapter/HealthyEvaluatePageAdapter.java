package com.example.administrator.personhealthrecord.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.example.administrator.personhealthrecord.mvp.health_evaluate.auto.AutoEvaluateFragment;
import com.example.administrator.personhealthrecord.mvp.health_evaluate.manual.ManualEvaluateFragment;

/**
 * Created by andy on 2017/8/3.
 */

public class HealthyEvaluatePageAdapter extends FragmentPagerAdapter {
    private SparseArray<String> mTitle = new SparseArray<>();

    public HealthyEvaluatePageAdapter(FragmentManager fm) {
        super(fm);
        mTitle.put(0, "自动评估");
        mTitle.put(1, "手动评估");
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AutoEvaluateFragment();
            case 1:
                return new ManualEvaluateFragment();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return mTitle.size();
    }
}
