package com.example.administrator.personhealthrecord.mvp.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.others.FragmentMgr;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;

/**
 * Created by Administrator on 2017-7-17.
 */

public class MainActivity extends AMainActivity {

    @BindView(R.id.main_container)
    FrameLayout mFrameLayout;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;
    @BindView(R.id.main_navigationView)
    NavigationView mMainNavigationView;
    @BindView(R.id.main_drawerLayout)
    DrawerLayout mDrawerLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.main;
    }

    @Override
    protected void initEvents() {
        mBottomBar = (BottomBar) findViewById(R.id.bottomBar);
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                mPresenter.onTabSelected(tabId);
            }
        });

        mMainNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // TODO: 2017-7-20
                switch (item.getItemId()) {
                    case R.id.menu_my_book:
                        break;
                    case R.id.menu_account_info:
                        break;
                    case R.id.menu_my_medical_record_folder:
                        break;
                    case R.id.menu_my_phr_management:
                        break;
                    case R.id.menu_health_assessment:
                        break;
                    default:
                        break;
                }
                mDrawerLayout.closeDrawer(Gravity.START);
                return true;
            }
        });
    }

    @Override
    public AMainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void dismissLoading() {
    }

    @Override
    public void setFragment(int position) {
        // TODO: 2017-7-20
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, FragmentMgr.getInstance().getFragment(position))
                .commit();
    }

    @Override
    public void openMenu() {
        mDrawerLayout.openDrawer(Gravity.START);
    }

    ActionBarDrawerToggle toggle;

    public void setUpWithToolbar(Toolbar toolbar) {
        toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }


}
