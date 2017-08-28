package com.example.administrator.personhealthrecord.mvp.health_evaluate;

import com.example.administrator.personhealthrecord.bean.AbstractObjectResult;
import com.example.administrator.personhealthrecord.bean.PHRBean;
import com.example.administrator.personhealthrecord.mvp.base.BaseModel;

import io.reactivex.Observer;

/**
 * Created by andy on 2017/7/24.
 */

abstract class IHealthyEvaluateModel extends BaseModel<IHealthyEvaluatePresenter> {
    IHealthyEvaluateModel(IHealthyEvaluatePresenter presenter) {
        super(presenter);
    }

    public abstract void getPHRData(Observer<AbstractObjectResult<PHRBean>> observer);

    public abstract void getPHRScore(Observer<AbstractObjectResult<Integer>> observer, PHRBean bean);
}
