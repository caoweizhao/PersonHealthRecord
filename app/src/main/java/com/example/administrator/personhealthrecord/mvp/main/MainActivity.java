package com.example.administrator.personhealthrecord.mvp.main;

import android.graphics.Color;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.mvp.log.LoginActivity;
import com.example.administrator.personhealthrecord.others.FragmentMgr;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
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
    SystemBarTintManager sm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mDrawerLayout.setScrimColor(0x00ffffff);
        sm = new SystemBarTintManager(this);
        sm.setStatusBarTintEnabled(true);
        setStatusBarTint(0xff05d09b);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.main;
    }


    @Override
    protected void initEvents() {
        fragmentMgr = new FragmentMgr(this, mFrameLayout);
        mBottomBar = (BottomBar) findViewById(R.id.bottomBar);
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                int pos = mBottomBar.findPositionForTabWithId(tabId);
                int color = getResources().getColor(R.color.colorPrimary);
                switch (pos) {
                    case 0:
                        color = getResources().getColor(R.color.colorPrimary);
                        break;
                    case 1:
                        break;
                    case 2:
                        color = Color.parseColor("#ff37474f");
                        break;
                    case 3:
                        color = Color.parseColor("#ff1565bf");
                        break;
                }
                setStatusBarTint(color);
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
                        Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
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
                // AnimateUtil.createCircularReveal(getWindow().getDecorView());
                return true;
            }
        });

        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                Log.d("MainActivity", "slideOffset" + slideOffset);
                if (slideOffset < 0.5) {
                    sm.setStatusBarTintEnabled(true);
                } else {
                    sm.setStatusBarTintEnabled(false);
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    @Override
    public AMainPresenter createPresenter() {
        return new MainPresenter();
    }


    @Override
    public void setFragment(int position) {
        fragmentMgr.getFragment(position);
    }

    @Override
    public void openMenu() {
        mDrawerLayout.openDrawer(Gravity.START);
        sm.setStatusBarTintEnabled(false);
    }

    ActionBarDrawerToggle toggle;

    Toolbar mToolbar;

    public void setUpWithToolbar(Toolbar toolbar) {
        mToolbar = toolbar;
        toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout != null) {
            if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
                mDrawerLayout.closeDrawer(Gravity.START);
                return;
            }
        }
        super.onBackPressed();
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void dismissLoading() {
    }

    public void setStatusBarTint(int color) {
        if (sm != null) {
            sm.setStatusBarTintColor(color);
            sm.setStatusBarAlpha(255 * 0.6f);
        }
        if(mToolbar != null){
            //mToolbar.setBackgroundColor(color);
        }
    }

    BottomBarTab tab;

    public void setBottomBarTint(int color) {
        if (tab == null) {
            tab = mBottomBar.getTabWithId(R.id.tab_community);
        }

        tab.setBarColorWhenSelected(color);
        setStatusBarTint(color);
    }

    public void refreshBottom() {
        mBottomBar.selectTabAtPosition(1);
    }
}
