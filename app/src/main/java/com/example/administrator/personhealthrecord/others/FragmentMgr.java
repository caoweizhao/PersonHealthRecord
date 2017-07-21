package com.example.administrator.personhealthrecord.others;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.administrator.personhealthrecord.mvp.checkpage.CheckPageFragment;
import com.example.administrator.personhealthrecord.mvp.healthynews.HealthyNewsFragment;
import com.example.administrator.personhealthrecord.mvp.homepage.HomePageFragment;
import com.example.administrator.personhealthrecord.mvp.socialpage.SocialPageFragment;

/**
 * Created by Administrator on 2017-7-20.
 */

public class FragmentMgr {
    private static final String TAG="FragmentMgr";
    public CheckPageFragment mCheckPageFragment;
    public HomePageFragment mHomePageFragment;
    public HealthyNewsFragment mHealthyNewsFragement;
    public SocialPageFragment mSocialPageFragment;

    private FragmentActivity context;
    private FrameLayout content;
    private FragmentPagerAdapter mFragmentPagerAdapter;
    public FragmentMgr(FragmentActivity context, FrameLayout frameLayout)
    {
        this.context=context;
        this.content=frameLayout;
        mFragmentPagerAdapter = new FragmentPagerAdapter(context.getSupportFragmentManager())
        {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return HomePageFragment.newInstance();
                    case 1:
                        return SocialPageFragment.newInstance();
                    case 2:
                        return HealthyNewsFragment.getInstance();
                    case 3:
                        return CheckPageFragment.newInstance();
                    default:
                        return HomePageFragment.newInstance();
                }
            }
            @Override
            public int getCount() {
                return 4;
            }
        };
    }


    public Fragment getFragment(int pos) {
        if (pos == 0) {
            ExcuteAdapter(0);
        }
        if(pos == 1){
            ExcuteAdapter(1);
        }
        if(pos == 2){
            ExcuteAdapter(2);
        }
        if (pos == 3) {
            ExcuteAdapter(3);
        }
        return null;
    }

    public void ExcuteAdapter(int position)
    {
        Fragment fragment = (Fragment) mFragmentPagerAdapter.instantiateItem(content, position);
        mFragmentPagerAdapter.setPrimaryItem(content, 0, fragment);
        mFragmentPagerAdapter.finishUpdate(content);
        Log.d(TAG, "ExcuteAdapter: "+mFragmentPagerAdapter);
    }
}


