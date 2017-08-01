package com.example.administrator.personhealthrecord.mvp.reserveorder;

import android.util.Log;

import com.example.administrator.personhealthrecord.bean.ResultUitlOfReserve;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfHealthyOrderBean;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by andy on 2017/7/31.
 */

public class ReserveOrderPresenterImpl extends IResreveOrderPresenter{
    private static final String TAG="ReserveOrderPresenter";
    @Override
    public IReserveOrderModle createModel() {
        return new ReserveOrderModleImpl(this);
    }

    @Override
    public void getHealthCheckeList() {
        Log.d(TAG, "getHealthCheckeList: 开始");
        Observer<ResultUtilOfHealthyOrderBean> observer=new Observer<ResultUtilOfHealthyOrderBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResultUtilOfHealthyOrderBean value) {
                if(value.getStatus().equals("status"))
                {

                }else
                {
                    if(!(value.getCollection()==null))
                    {
                        Log.d(TAG, "onNext: "+value.getCollection().get(0).getPhoneNumber());
                    }

                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        mModel.getHealthyCheckList(observer);
    }

}
