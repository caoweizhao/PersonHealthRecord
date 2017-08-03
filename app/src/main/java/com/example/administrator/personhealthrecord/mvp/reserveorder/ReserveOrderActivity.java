package com.example.administrator.personhealthrecord.mvp.reserveorder;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.adapter.ReserveOrderFragmentPageAdapter;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.ResultUitlOfReserve;
import com.example.administrator.personhealthrecord.mvp.base.MvpActivity;

import java.util.List;

import butterknife.BindView;

public class ReserveOrderActivity extends BaseActivity {

    @BindView(R.id.reserve_order_tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.reserve_order_viewPager)
    ViewPager viewPager;
    private FragmentManager fmanager;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_reserve_order;
    }

    @Override
    protected void initData() {
        super.initData();
        fmanager=getSupportFragmentManager();
        viewPager.setAdapter(new ReserveOrderFragmentPageAdapter(fmanager));
        viewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(viewPager);
        initToolbar("我的预约",true,null);
    }

}
