package com.example.administrator.personhealthrecord.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017-7-19.
 */

public class BaseFragment extends Fragment {

    Unbinder mUnbinder;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mUnbinder.unbind();
    }
}
