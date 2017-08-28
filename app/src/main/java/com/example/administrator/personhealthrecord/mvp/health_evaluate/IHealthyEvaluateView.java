package com.example.administrator.personhealthrecord.mvp.health_evaluate;

import com.example.administrator.personhealthrecord.bean.PHRBean;
import com.example.administrator.personhealthrecord.mvp.base.MvpFragment;

/**
 * Created by andy on 2017/7/24.
 */

public abstract class IHealthyEvaluateView extends MvpFragment<IHealthyEvaluatePresenter>{
    public abstract void OnPHRReady(PHRBean bean);
    //获取PHR分数
    public abstract void OnPHRScoreReady(int score);
}
