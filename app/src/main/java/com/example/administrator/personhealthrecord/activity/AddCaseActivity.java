package com.example.administrator.personhealthrecord.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.mvp.base.BaseActivity;

import butterknife.BindView;

public class AddCaseActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.add_case;
    }
}
