package com.example.administrator.personhealthrecord.others;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.mvp.checkpage.CheckPageFragment;
import com.example.administrator.personhealthrecord.mvp.healthynews.HealthyNewsFragment;
import com.example.administrator.personhealthrecord.mvp.homepage.HomePageFragment;
import com.example.administrator.personhealthrecord.mvp.main.MainActivity;
import com.example.administrator.personhealthrecord.mvp.socialpage.SocialPageFragment;

/**
 * Created by Administrator on 2017-7-20.
 */

public class FragmentMgr {
    public static final int HOME_PAGE = 0;
    public static final int SOCIAL_PAGE = 1;
    public static final int HEALTHY_NEWS_PAGE = 2;
    public static final int CHECK_PAGE = 3;

    private static final String TAG = "FragmentMgr";
    private SocialPageFragment mSocialPageFragment;

    private AppCompatActivity context;
    private FrameLayout content;
    private FragmentPagerAdapter mFragmentPagerAdapter;

    public FragmentMgr(AppCompatActivity context, FrameLayout frameLayout) {
        this.context = context;
        this.content = frameLayout;
        mFragmentPagerAdapter = new FragmentPagerAdapter(context.getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case HOME_PAGE:
                        return HomePageFragment.newInstance();
                    case SOCIAL_PAGE:
                        return mSocialPageFragment = SocialPageFragment.newInstance();
                    case HEALTHY_NEWS_PAGE:
                        return HealthyNewsFragment.getInstance();
                    case CHECK_PAGE:
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

    public void setFragment(int pos) {
        executeAdapter(pos);
    }

    private void executeAdapter(int position) {
        Fragment fragment = (Fragment) mFragmentPagerAdapter.instantiateItem(content, position);
        mFragmentPagerAdapter.setPrimaryItem(content, 0, fragment);
        mFragmentPagerAdapter.finishUpdate(content);
        if (position == SOCIAL_PAGE) {
            int pos = ((SocialPageFragment) fragment).getCurrentPosition();
            ((MainActivity) context).setBottomBarTint(Contract.colors[pos]);
            ((SocialPageFragment) fragment).startImageAnim();
        } else {
            /**
             * 暂停SocialPage中的图片缩放动画
             */
            if (mSocialPageFragment != null) {
                mSocialPageFragment.stopImageAnim();
            }
        }
    }
}


