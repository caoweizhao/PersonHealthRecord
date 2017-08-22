package com.example.administrator.personhealthrecord.mvp.reserve_order;

import android.util.Log;

import com.example.administrator.personhealthrecord.bean.AppointmentBean;
import com.example.administrator.personhealthrecord.bean.ReserveOrderBean;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfHealthyOrderBean;
import com.example.administrator.personhealthrecord.mvp.reserve_order.appointmenorder.AppointmentOrderFragment;
import com.example.administrator.personhealthrecord.mvp.reserve_order.healthcheckorder.HealthyCheckOrderFragment;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by andy on 2017/7/31.
 */

public class ReserveOrderPresenterImpl extends IReserveOrderPresenter {
    private static final String TAG = "ReserveOrderPresenter";

    @Override
    public IReserveOrderModel createModel() {
        return new ReserveOrderModelImpl(this);
    }

    @Override
    public void getHealthCheckedList() {
        Observer<ResultUtilOfHealthyOrderBean<ReserveOrderBean>> observer = new Observer<ResultUtilOfHealthyOrderBean<ReserveOrderBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResultUtilOfHealthyOrderBean<ReserveOrderBean> value) {
                if (!value.getStatus().equals("success")) {
                    ((HealthyCheckOrderFragment) mView).NoPackage();
                } else {
                    if (!(value.getCollection() == null)) {
                        ((HealthyCheckOrderFragment) mView).OnPackageReady(value.getCollection());
                        Log.d(TAG, "onNext: " + value.getCollection().get(0).getHospitalName());
                    } else {
                        ((HealthyCheckOrderFragment) mView).NoPackage();
                    }

                }

            }

            @Override
            public void onError(Throwable e) {
                ((AppointmentOrderFragment) mView).NoPackage();
            }

            @Override
            public void onComplete() {
            }
        };
        mModel.getHealthyCheckList(observer);
    }

    @Override
    public void getAppointmentList() {
        Observer<ResultUtilOfHealthyOrderBean<AppointmentBean>> observer = new Observer<ResultUtilOfHealthyOrderBean<AppointmentBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResultUtilOfHealthyOrderBean<AppointmentBean> value) {
                if (value.getStatus().equals("success")) {
                    if (!(value.getCollection() == null)) {
                        Log.d(TAG, "onNext: " + value.getStatus());
                        ((AppointmentOrderFragment) mView).OnAppointmentReady(value.getCollection());
                    } else {
                        ((AppointmentOrderFragment) mView).NoPackage();
                    }
                } else {
                    ((AppointmentOrderFragment) mView).NoPackage();

                }

            }

            @Override
            public void onError(Throwable e) {
                ((AppointmentOrderFragment) mView).NoPackage();
            }

            @Override
            public void onComplete() {
            }
        };
        mModel.getAppointmentList(observer);
    }

}
