package com.example.administrator.personhealthrecord.mvp.reserveorder;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.adapter.ReserveOrderFragmentPageAdapter;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.ResultUitlOfReserve;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.mvp.base.MvpActivity;
import com.example.administrator.personhealthrecord.mvp.registandlogin.LoginActivity;
import com.example.administrator.personhealthrecord.util.DialogUtil;

import java.util.List;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;

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
        initToolbar("我的预约",true,null);
    }

    @Override
    protected void onResume() {
        if(Contract.IsLogin.equals(Contract.Login))
        {
            fmanager=getSupportFragmentManager();
            viewPager.setAdapter(new ReserveOrderFragmentPageAdapter(fmanager));
            viewPager.setOffscreenPageLimit(2);
            mTabLayout.setupWithViewPager(viewPager);
        }else
            gotToLogin();
        super.onResume();
    }

    public void gotToLogin()
    {
        DialogUtil.getLoginDialog(this).show();
    }
}
