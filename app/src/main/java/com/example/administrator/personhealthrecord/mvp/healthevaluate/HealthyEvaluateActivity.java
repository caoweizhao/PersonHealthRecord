package com.example.administrator.personhealthrecord.mvp.healthevaluate;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.adapter.HealthyEvaluatePageAdapter;
import com.example.administrator.personhealthrecord.base.BaseActivity;

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
        pageAdapter=new HealthyEvaluatePageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);
        mTab.setupWithViewPager(viewPager);
        initToolbar("PHR",true,null);
    }
}
