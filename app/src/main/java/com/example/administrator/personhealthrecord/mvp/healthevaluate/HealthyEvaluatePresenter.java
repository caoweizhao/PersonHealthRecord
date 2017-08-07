package com.example.administrator.personhealthrecord.mvp.healthevaluate;

import android.util.Log;

import com.example.administrator.personhealthrecord.bean.AbstractObjectResult;
import com.example.administrator.personhealthrecord.bean.PHRBean;
import com.example.administrator.personhealthrecord.mvp.healthevaluate.auto.AutoEvaluateFragment;
import com.example.administrator.personhealthrecord.util.ToastUitl;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by andy on 2017/8/3.
 */

public class HealthyEvaluatePresenter extends IHealthyEvaluatePresenter{
    private static final String TAG="HealthyEvaluate";
    @Override
    public void getPHRdata() {
        Observer<AbstractObjectResult<PHRBean>> observer=new Observer<AbstractObjectResult<PHRBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AbstractObjectResult<PHRBean> value) {
                    Log.d(TAG, "onNext: "+value.getMessage());
                if(value.getStatus().equals("success"))
                {
                    mView.OnPHRReady(value.getObject());
                }else
                {
                    ToastUitl.Toast(value.getMessage());
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUitl.Toast(e.toString());
            }

            @Override
            public void onComplete() {

            }
        };
        mModel.getPHRdaata(observer);
    }

    @Override
    public void getPHRScore(PHRBean bean) {
        Observer<AbstractObjectResult<Integer>> observer=new Observer<AbstractObjectResult<Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AbstractObjectResult<Integer> value) {
                if(value.getStatus().equals("success"))
                {
                    mView.OnPHRScoreReady(value.getObject());

                }else
                {
                    ToastUitl.Toast(value.getMessage());
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e.toString());
            }

            @Override
            public void onComplete() {

            }
        };
        mModel.getPHRScore(observer,bean);
    }

    @Override
    public IHealthyEvaluateModle createModel() {
        return new HealthyEvaluateModleImpl(this);
    }
}
