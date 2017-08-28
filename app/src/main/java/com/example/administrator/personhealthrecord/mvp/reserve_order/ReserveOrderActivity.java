package com.example.administrator.personhealthrecord.mvp.reserve_order;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.adapter.ReserveOrderFragmentPageAdapter;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.util.DialogUtil;

import butterknife.BindView;

public class ReserveOrderActivity extends BaseActivity {

    @BindView(R.id.reserve_order_tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.reserve_order_viewPager)
    ViewPager viewPager;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_reserve_order;
    }

    @Override
    protected void initData() {
        super.initData();
        if (!Contract.IsLogin.equals(Contract.Login))
            gotToLogin();
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new ReserveOrderFragmentPageAdapter(fragmentManager));
        viewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(viewPager);
        initToolbar("我的预约", true, null);
    }

    public void gotToLogin() {
        DialogUtil.getLoginDialog(this).show();
    }
}
