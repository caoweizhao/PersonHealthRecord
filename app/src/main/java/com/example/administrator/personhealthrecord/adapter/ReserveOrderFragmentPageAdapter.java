package com.example.administrator.personhealthrecord.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.example.administrator.personhealthrecord.mvp.reserveorder.TestFragment;
import com.example.administrator.personhealthrecord.mvp.reserveorder.healthcheckorder.HealthyCheckOrderFragment;


/**
 * Created by andy on 2017/7/31.
 */

public class ReserveOrderFragmentPageAdapter extends FragmentPagerAdapter{
    public SparseArray<String> mTitles = new SparseArray<>();
    public ReserveOrderFragmentPageAdapter(FragmentManager fm) {
        super(fm);
        mTitles.put(0, "门诊预约");
        mTitles.put(1, "体检预约");
        mTitles.put(2, "设备租约");
    }

    @Override
    public Fragment getItem(int position) {
        switch(position)
            {
                case 0:
                    return new TestFragment();
                case 1:
                    return new HealthyCheckOrderFragment();
                case 2:
                    return new TestFragment();
                default:

                    break;
            }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }
}
