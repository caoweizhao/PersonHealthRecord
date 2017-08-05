package com.example.administrator.personhealthrecord.mvp.healthevaluate;

import com.example.administrator.personhealthrecord.bean.PHRBean;
import com.example.administrator.personhealthrecord.mvp.base.BasePresenter;


/**
 * Created by andy on 2017/7/24.
 */

public abstract class IHealthyEvaluatePresenter extends BasePresenter<IHealthyEvaluateView,IHealthyEvaluateModle> {
    public abstract void getPHRdata();
    public abstract void getPHRScore(PHRBean phrBean);
}
