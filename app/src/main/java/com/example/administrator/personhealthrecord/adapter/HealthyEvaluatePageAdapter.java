package com.example.administrator.personhealthrecord.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.example.administrator.personhealthrecord.mvp.healthevaluate.auto.AutoEvaluateFragment;
import com.example.administrator.personhealthrecord.mvp.healthevaluate.manual.ManualEvaluateFragment;

/**
 * Created by andy on 2017/8/3.
 */

public class HealthyEvaluatePageAdapter extends FragmentPagerAdapter{
    SparseArray<String> mTItle=new SparseArray<>();
    public HealthyEvaluatePageAdapter(FragmentManager fm) {
        super(fm);
        mTItle.put(0,"自动评估");
        mTItle.put(1,"手动评估");
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTItle.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position)
            {
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
        return mTItle.size();
    }
}
