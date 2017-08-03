package com.example.administrator.personhealthrecord.mvp.reserveorder;

import android.util.Log;

import com.example.administrator.personhealthrecord.bean.AppointmentBean;
import com.example.administrator.personhealthrecord.bean.ReserOrderBean;
import com.example.administrator.personhealthrecord.bean.ResultUitlOfReserve;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfHealthyOrderBean;
import com.example.administrator.personhealthrecord.mvp.reserveorder.appointmenorder.AppointmentOrderFragment;
import com.example.administrator.personhealthrecord.mvp.reserveorder.healthcheckorder.HealthyCheckOrderFragment;
import com.example.administrator.personhealthrecord.util.ToastUitl;

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
        Observer<ResultUtilOfHealthyOrderBean<ReserOrderBean>> observer=new Observer<ResultUtilOfHealthyOrderBean<ReserOrderBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResultUtilOfHealthyOrderBean<ReserOrderBean> value) {
                if(!value.getStatus().equals("success"))
                {
                    ToastUitl.Toast(value.getMessage());
                }else
                {
                    if(!(value.getCollection()==null))
                    {
                        ((HealthyCheckOrderFragment)mView).OnPackageReady(value.getCollection());
                        Log.d(TAG, "onNext: "+value.getCollection().get(0).getHospitalName());
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

    @Override
    public void getAppoitmentList() {
        Observer<ResultUtilOfHealthyOrderBean<AppointmentBean>> observer=new Observer<ResultUtilOfHealthyOrderBean<AppointmentBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResultUtilOfHealthyOrderBean<AppointmentBean> value) {
                if(value.getStatus().equals("status"))
                {

                }else
                {
                    if(!(value.getCollection()==null))
                    {
                        Log.d(TAG, "onNext: "+value.getStatus());
                        ((AppointmentOrderFragment)mView).OnAppointmentReady(value.getCollection());
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
        mModel.getAppoitmentList(observer);
    }

}
