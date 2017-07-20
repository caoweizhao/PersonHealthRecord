package com.example.administrator.personhealthrecord.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.example.administrator.personhealthrecord.mvp.socialpage.medical.MedicalFragment;
import com.example.administrator.personhealthrecord.mvp.socialpage.news.NewsFragment;

/**
 * Created by Administrator on 2017-7-20.
 */

public class SocialPageViewPagerAdapter extends FragmentPagerAdapter {

    private SparseArray<Fragment> mFragmentSparseArray = new SparseArray<>(4);

    public SocialPageViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragmentSparseArray.get(position) == null) {
            Fragment fragment;
            switch (position) {
                case 0:
                    fragment = NewsFragment.newInstance();
                    break;
                case 1:
                    fragment = MedicalFragment.newInstance();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:

                    break;
            }
        }
        return mFragmentSparseArray.get(position);
    }

    @Override
    public int getCount() {
        return 4;
    }
}
