package com.example.administrator.personhealthrecord.mvp.health_evaluate;

import com.example.administrator.personhealthrecord.bean.PHRBean;
import com.example.administrator.personhealthrecord.mvp.base.BasePresenter;


/**
 * Created by andy on 2017/7/24.
 */

public abstract class IHealthyEvaluatePresenter extends BasePresenter<IHealthyEvaluateView,IHealthyEvaluateModel> {
    public abstract void getPHRdata();
    public abstract void getPHRScore(PHRBean phrBean);
}
