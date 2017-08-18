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
    private static final String TAG = "FragmentMgr";
    public CheckPageFragment mCheckPageFragment;
    public HomePageFragment mHomePageFragment;
    public HealthyNewsFragment mHealthyNewsFragement;
    public SocialPageFragment mSocialPageFragment;

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
                    case 0:
                        return HomePageFragment.newInstance();
                    case 1:
                        return mSocialPageFragment = SocialPageFragment.newInstance();
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
            ExecuteAdapter(0);
        }
        if (pos == 1) {
            ExecuteAdapter(1);
        }
        if (pos == 2) {
            ExecuteAdapter(2);
        }
        if (pos == 3) {
            ExecuteAdapter(3);
        }
        return null;
    }

    public void ExecuteAdapter(int position) {
        Fragment fragment = (Fragment) mFragmentPagerAdapter.instantiateItem(content, position);
        fragment.setHasOptionsMenu(true);
        mFragmentPagerAdapter.setPrimaryItem(content, 0, fragment);
        mFragmentPagerAdapter.finishUpdate(content);
        if (position == 1) {
            int pos = ((SocialPageFragment) fragment).getCurrentPosition();
            ((MainActivity) context).setBottomBarTint(Contract.colors[pos]);
            ((SocialPageFragment) fragment).startImageAnim();
            //((MainActivity) context).refreshBottom();
        } else {
            if (mSocialPageFragment != null) {
                mSocialPageFragment.stopImageAnim();
            }
        }
    }
}


