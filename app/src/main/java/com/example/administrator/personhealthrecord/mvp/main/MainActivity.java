package com.example.administrator.personhealthrecord.mvp.main;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Outline;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.activity.AboutActivity;
import com.example.administrator.personhealthrecord.activity.CaseListActivity;
import com.example.administrator.personhealthrecord.activity.ProfileActivity;
import com.example.administrator.personhealthrecord.activity.SelfPHRActivity;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.mvp.healthevaluate.HealthyEvaluateActivity;
import com.example.administrator.personhealthrecord.mvp.registandlogin.LoginActivity;
import com.example.administrator.personhealthrecord.others.FragmentMgr;
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
    public DrawerLayout mDrawerLayout;

    ImageView mAvator;

    @Override
    protected void initData() {
        mAvator = (ImageView) mMainNavigationView.getHeaderView(0).findViewById(R.id.avator);

        mAvator.post(new Runnable() {
            @Override
            public void run() {
                final int width = mAvator.getWidth();
                final int height = mAvator.getHeight();
                int length = Math.min(width, height);

                int centerX = width / 2;
                int centerY = height / 2;

                final int left;
                final int top;
                final int right;
                final int bottom;

                if (length == width) {
                    left = 0;
                    top = centerY - length / 2;
                    right = width;
                    bottom = centerY + length / 2;
                } else {
                    left = centerX - length / 2;
                    top = 0;
                    right = centerX + length / 2;
                    bottom = height;
                }

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ViewOutlineProvider outlineProvider = new ViewOutlineProvider() {
                        @Override
                        public void getOutline(View view, Outline outline) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                outline.setOval(left, top, right, bottom);
                            }
                        }
                    };
                    mAvator.setClipToOutline(true);
                    mAvator.setOutlineProvider(outlineProvider);
                }
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.main;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void initEvents() {
        mAvator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
                            mAvator, "avator").toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });

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
                        color = Color.parseColor(getString(R.string.health_color));
                        break;
                    case 3:
                        color = Color.parseColor(getString(R.string.check_page_color));
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
//                        Intent intent1 = new Intent(MainActivity.this, TestActivity.class);
//                        startActivity(intent1);
                        break;
                    case R.id.menu_account_info:
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.menu_my_medical_record_folder:
                        Intent caseIntent = new Intent(MainActivity.this, CaseListActivity.class);
                        startActivity(caseIntent);
                        break;
                    case R.id.menu_my_phr_management:
                        Intent phrIntent = new Intent(MainActivity.this, SelfPHRActivity.class);
                        startActivity(phrIntent);
                        break;
                    case R.id.menu_health_assessment:
                        Intent intent3 = new Intent(MainActivity.this, HealthyEvaluateActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.about_app:
                        Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
                        startActivity(aboutIntent);
                        return true;
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
    protected void onResume() {
        super.onResume();
        if (Contract.IsLogin.equals(Contract.Login)) {
            mPresenter.requestAvator();
        }
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

    @Override
    public void updateAvator(String url) {
        Glide.with(this)
                .load(url)
                .crossFade()
                .error(R.drawable.chat_left_human)
                .into(mAvator);
    }

    ActionBarDrawerToggle toggle;

    Toolbar mToolbar;

    public void setUpWithToolbar(Toolbar toolbar) {
        mToolbar = toolbar;
        toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
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
        };
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    long mLastClickTime = 0;

    @Override
    public void onBackPressed() {
        if (mDrawerLayout != null) {
            if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
                mDrawerLayout.closeDrawer(Gravity.START);
                return;
            }
        }
        if ((System.currentTimeMillis() - mLastClickTime) <= 1500) {
            super.onBackPressed();
        } else {
            mLastClickTime = System.currentTimeMillis();
            Snackbar.make(mDrawerLayout, "再按一次退出应用", Snackbar.LENGTH_SHORT).show();
        }
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
            sm.setStatusBarAlpha(255 * 0.9f);
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
