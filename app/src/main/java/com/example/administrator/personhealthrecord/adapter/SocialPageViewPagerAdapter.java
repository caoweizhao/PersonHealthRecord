package com.example.administrator.personhealthrecord.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.util.SparseArray;

import com.example.administrator.personhealthrecord.mvp.socialpage.disease.DiseaseFragment;
import com.example.administrator.personhealthrecord.mvp.socialpage.immune.ImmuneFragment;
import com.example.administrator.personhealthrecord.mvp.socialpage.medical.MedicalFragment;
import com.example.administrator.personhealthrecord.mvp.socialpage.news.NewsFragment;

/**
 * Created by Administrator on 2017-7-20.
 */

public class SocialPageViewPagerAdapter extends FragmentPagerAdapter {

    public SparseArray<String> mTitles = new SparseArray<>();
    public SparseArray<Fragment> mFragmentSparseArray = new SparseArray<>();

    {
        mTitles.put(0, "新闻管理");
        mTitles.put(1, "药品信息");
        mTitles.put(2, "慢病信息");
        mTitles.put(3, "计划免疫");
    }



    public SocialPageViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentSparseArray.put(1, MedicalFragment.newInstance());
        mFragmentSparseArray.put(2, DiseaseFragment.newInstance());
        mFragmentSparseArray.put(3, ImmuneFragment.newInstance());
        mFragmentSparseArray.put(0, NewsFragment.newInstance());
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("aaa","getItem");
        return mFragmentSparseArray.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentSparseArray.size();
    }
}
