package com.example.administrator.personhealthrecord.mvp.health_evaluate;

import com.example.administrator.personhealthrecord.bean.AbstractObjectResult;
import com.example.administrator.personhealthrecord.bean.PHRBean;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.mvp.health_evaluate.api.HealthyEvaluateService;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by andy on 2017/8/3.
 */

class HealthyEvaluateModelImpl extends IHealthyEvaluateModel {
    HealthyEvaluateModelImpl(IHealthyEvaluatePresenter presenter) {
        super(presenter);
    }

    @Override
    public void getPHRData(Observer<AbstractObjectResult<PHRBean>> observer) {
        Retrofit retrofit= RetrofitUtil.getRetrofit();
        HealthyEvaluateService service=retrofit.create(HealthyEvaluateService.class);
        service.getPHRData(Contract.cookie)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getPHRScore(Observer<AbstractObjectResult<Integer>> observer,PHRBean bean) {
        Retrofit retrofit= RetrofitUtil.getRetrofit();
        HealthyEvaluateService service=retrofit.create(HealthyEvaluateService.class);
        service.getPHR(Contract.cookie,bean)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
