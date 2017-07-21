package com.example.administrator.personhealthrecord.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;


import com.example.administrator.personhealthrecord.mvp.socialpage.disease.DiseaseFragment;
import com.example.administrator.personhealthrecord.mvp.socialpage.immune.ImmuneFragment;
import com.example.administrator.personhealthrecord.mvp.socialpage.medical.MedicalFragment;
import com.example.administrator.personhealthrecord.mvp.socialpage.news.NewsFragment;

/**
 * Created by Administrator on 2017-7-20.
 */

public class SocialPageViewPagerAdapter extends FragmentPagerAdapter {

    private SparseArray<Fragment> mFragmentSparseArray = new SparseArray<>(4);


    public SparseArray<String> mTitles = new SparseArray<>();
    {
        mTitles.put(0,"新闻管理");
        mTitles.put(1,"药品信息");
        mTitles.put(2,"慢病信息");
        mTitles.put(3,"计划免疫");
    }

    public SocialPageViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override

    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override

    public Fragment getItem(int position) {
        if (mFragmentSparseArray.get(position) == null) {
            Fragment fragment;
            switch (position) {


                case 1:
                    fragment = MedicalFragment.newInstance();
                    break;
                case 2:

                    fragment = DiseaseFragment.newInstance();
                    break;
                case 3:
                    fragment = ImmuneFragment.newInstance();
                    break;
                case 0:
                default:
                    fragment = NewsFragment.newInstance();
                    break;
            }
            mFragmentSparseArray.put(position,fragment);
            return fragment;
        }


        return mFragmentSparseArray.get(position);
    }

    @Override
    public int getCount() {
        return 4;
    }
}
