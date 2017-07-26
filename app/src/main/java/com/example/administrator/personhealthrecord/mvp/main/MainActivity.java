package com.example.administrator.personhealthrecord.mvp.main;

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
import android.widget.FrameLayout;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.mvp.registandlogin.LoginActivity;
import com.example.administrator.personhealthrecord.others.FragmentMgr;
import com.example.administrator.personhealthrecord.util.ToastUitl;
import com.readystatesoftware.systembartint.SystemBarTintManager;
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
    SystemBarTintManager sm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDrawerLayout.setScrimColor(0x00ffffff);
        sm = new SystemBarTintManager(this);
        sm.setStatusBarTintEnabled(true);
        setStatusBarTint(0xff05d09b);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.main;
    }

    boolean isFirstIn = true;

    @Override
    protected void initEvents() {
        fragmentMgr = new FragmentMgr(this, mFrameLayout);
        mBottomBar = (BottomBar) findViewById(R.id.bottomBar);
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                setStatusBarTint(0xff05d09b);
                mPresenter.onTabSelected(tabId);
               /* if (isFirstIn) {
                    isFirstIn = false;
                }else{
                    AnimateUtil.createCircularReveal(mFrameLayout);
                }*/
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
                        if(Contract.IsLogin.equals(Contract.Login))
                        {
                            ToastUitl.Toast("已经登录");
                        }
                        else
                        {
                            Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
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
    }

    @Override
    public AMainPresenter createPresenter() {
        return new MainPresenter();
    }


    @Override
    public void setFragment(int position) {
        // TODO: 2017-7-20
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.main_container, FragmentMgr.getInstance().getFragment(position))
//                .commit();
        fragmentMgr.getFragment(position);
    }

    @Override
    public void openMenu() {
        mDrawerLayout.openDrawer(Gravity.START);
    }

    ActionBarDrawerToggle toggle;

    public void setUpWithToolbar(final Toolbar toolbar) {
        toolbar.post(new Runnable() {
            @Override
            public void run() {
                Log.d("MainActivity", ":" + toolbar.getMeasuredHeight());
            }
        });
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
    }
}
