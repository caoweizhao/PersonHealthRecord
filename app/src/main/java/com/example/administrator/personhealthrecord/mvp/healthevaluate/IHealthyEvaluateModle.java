package com.example.administrator.personhealthrecord.mvp.healthevaluate;

import com.example.administrator.personhealthrecord.bean.AbstractObjectResult;
import com.example.administrator.personhealthrecord.bean.Loginbean;
import com.example.administrator.personhealthrecord.bean.PHRBean;
import com.example.administrator.personhealthrecord.bean.RegistBean;
import com.example.administrator.personhealthrecord.mvp.base.BaseModel;

import io.reactivex.Observer;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by andy on 2017/7/24.
 */

public abstract class IHealthyEvaluateModle extends BaseModel<IHealthyEvaluatePresenter>{
    public IHealthyEvaluateModle(IHealthyEvaluatePresenter presenter) {
        super(presenter);
    }
    public abstract void getPHRdaata(Observer<AbstractObjectResult<PHRBean>> observer);
    public abstract void getPHRScore(Observer<AbstractObjectResult<Integer>> observer,PHRBean bean);
}
