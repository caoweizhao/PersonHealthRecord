package com.example.administrator.personhealthrecord.mvp.healthevaluate;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.adapter.HealthyEvaluatePageAdapter;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.mvp.registandlogin.LoginActivity;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;

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
        initToolbar("PHR",true,null);
        if(!Contract.IsLogin.equals(Contract.Login)) {
            gotToLogin();
        }else
        {
            pageAdapter=new HealthyEvaluatePageAdapter(getSupportFragmentManager());
            viewPager.setAdapter(pageAdapter);
            mTab.setupWithViewPager(viewPager);
        }
    }

    @Override
    protected void onResume() {
        if(Contract.IsLogin.equals(Contract.Login)) {
            pageAdapter=new HealthyEvaluatePageAdapter(getSupportFragmentManager());
            viewPager.setAdapter(pageAdapter);
            mTab.setupWithViewPager(viewPager);
        }
        super.onResume();

    }

    public void gotToLogin()
    {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("您还没有登录！")
                .setContentText("是否去登录界面？")
                .setCancelText("不了~")
                .setConfirmText("去登陆->")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        finish();
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                        Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                })
                .show();
    }
}
