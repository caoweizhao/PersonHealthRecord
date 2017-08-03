package com.example.administrator.personhealthrecord.mvp.healthevaluate;

import com.example.administrator.personhealthrecord.util.RetrofitUtil;

import retrofit2.Retrofit;

/**
 * Created by andy on 2017/8/3.
 */

public class HealthyEvaluateModleImpl extends IHealthyEvaluateModle{
    public HealthyEvaluateModleImpl(IHealthyEvaluatePresenter presenter) {
        super(presenter);
    }

    @Override
    public void getPHRdaata() {
        Retrofit retrofit= RetrofitUtil.getRetrofit();

    }
}
