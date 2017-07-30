package com.example.administrator.personhealthrecord.mvp.reserve;

import android.util.Log;

import com.example.administrator.personhealthrecord.activity.ReserveNowActivity;
import com.example.administrator.personhealthrecord.bean.HospitalBean;
import com.example.administrator.personhealthrecord.bean.PackageBean;
import com.example.administrator.personhealthrecord.bean.ReserveBean;
import com.example.administrator.personhealthrecord.bean.ResultUitlOfReserve;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfHospitalList;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfPackageBean;
import com.example.administrator.personhealthrecord.util.ToastUitl;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by andy on 2017/7/27.
 */

public class ReservePresenterImpl extends IResrevePresenter{
    private static final String TAG="ReservePresenterImpl";
    @Override
    public void getPackageHospitals() {
        Observer<ResultUtilOfHospitalList> observer=new Observer<ResultUtilOfHospitalList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResultUtilOfHospitalList value) {
                List<HospitalBean> list=new ArrayList<>();
                if(value.getStatus().equals("success"))
                {
                    OnhospitalReady(value.getCollection());
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
                Log.d(TAG, "onComplete: ");
            }
        };
        mModel.getPackageHospitals(observer);

    }

    @Override
    public void OnhospitalReady(List<HospitalBean> list) {
        mView.updataHostpitals(list);
    }

    @Override
    public void getPackage(int id) {
        Observer<ResultUtilOfPackageBean> observer=new Observer<ResultUtilOfPackageBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResultUtilOfPackageBean value) {
                List<PackageBean> list=new ArrayList<>();
                Log.d(TAG, "onNext: "+value.getCollection().size());
                if(value.getStatus().equals("success"))
                {
                    OnPackageReadey(value.getCollection());
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
                Log.d(TAG, "onComplete: ");
            }
        };
        mModel.getPackage(observer,id);
    }

    @Override
    public void OnPackageReadey(List<PackageBean> list) {
        mView.updatePackgets(list);
    }

    @Override
    public void ReserveNow(long StartTime, long EndTime, String name, String phoneNumber,int id) {
        Observer<ResultUitlOfReserve> observer=new Observer<ResultUitlOfReserve>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResultUitlOfReserve value) {
                List<PackageBean> list=new ArrayList<>();
                Log.d(TAG, "onNext: "+value.getObject().getMedicalStatus());
                if(value.getStatus().equals("success"))
                {
                    ToastUitl.Toast(value.getMessage());
                    ((ReserveNowActivity)mView).ReserveSuccess();
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
                Log.d(TAG, "onComplete: ");
            }
        };
        ReserveBean bean=new ReserveBean();
        bean.setStartTime(StartTime);
        bean.setEndTime(EndTime);
        bean.setName(name);
        bean.setPhoneNumber(phoneNumber);
        bean.setId(id);
        mModel.ReserveNow(observer,bean);
    }

    @Override
    public IReserveModle createModel() {
        return new ReserveModleImpl(this);
    }
}
