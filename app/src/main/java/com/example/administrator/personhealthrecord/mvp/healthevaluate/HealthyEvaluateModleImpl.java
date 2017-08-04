package com.example.administrator.personhealthrecord.mvp.healthevaluate;

import com.example.administrator.personhealthrecord.bean.AbstractObjectResult;
import com.example.administrator.personhealthrecord.bean.PHRBean;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.mvp.healthevaluate.api.HealthyEvaluateService;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

/**
 * Created by andy on 2017/8/3.
 */

public class HealthyEvaluateModleImpl extends IHealthyEvaluateModle{
    public HealthyEvaluateModleImpl(IHealthyEvaluatePresenter presenter) {
        super(presenter);
    }

    @Override
    public void getPHRdaata(Observer<AbstractObjectResult<PHRBean>> observer) {
        Retrofit retrofit= RetrofitUtil.getRetrofit();
        HealthyEvaluateService service=retrofit.create(HealthyEvaluateService.class);
        service.getPHRdata(Contract.cookie)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
