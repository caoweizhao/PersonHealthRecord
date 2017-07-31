package com.example.administrator.personhealthrecord.mvp.reserveorder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.personhealthrecord.R;

/**
 * Created by andy on 2017/7/31.
 */

public class TestFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.reserve_order_health_check,container,false);
        return view;
    }
}
