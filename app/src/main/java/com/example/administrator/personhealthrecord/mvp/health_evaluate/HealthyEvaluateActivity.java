package com.example.administrator.personhealthrecord.mvp.health_evaluate;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.adapter.HealthyEvaluatePageAdapter;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.util.DialogUtil;

import butterknife.BindView;

public class HealthyEvaluateActivity extends BaseActivity {
    @BindView(R.id.healthy_evaluate_tabLayout)
    TabLayout mTab;
    @BindView(R.id.healthy_evaluate_viewPager)
    ViewPager viewPager;
    private HealthyEvaluatePageAdapter pageAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_healthy_evaluate;
    }

    @Override
    protected void initData() {
        super.initData();
        initToolbar("PHR", true, null);
        if (!Contract.IsLogin.equals(Contract.Login)) {
            gotToLogin();
        } else {
            pageAdapter = new HealthyEvaluatePageAdapter(getSupportFragmentManager());
            viewPager.setAdapter(pageAdapter);
            mTab.setupWithViewPager(viewPager);
        }
    }

    @Override
    protected void onResume() {
        if (Contract.IsLogin.equals(Contract.Login)) {
            pageAdapter = new HealthyEvaluatePageAdapter(getSupportFragmentManager());
            viewPager.setAdapter(pageAdapter);
            mTab.setupWithViewPager(viewPager);
        }
        super.onResume();
    }

    public void gotToLogin() {
        DialogUtil.getLoginDialog(this).show();
    }
}
