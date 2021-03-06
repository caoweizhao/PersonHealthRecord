package com.example.administrator.personhealthrecord.mvp.reserve;

import com.example.administrator.personhealthrecord.bean.HospitalBean;
import com.example.administrator.personhealthrecord.bean.PackageBean;
import com.example.administrator.personhealthrecord.mvp.base.BasePresenter;

import java.util.List;

/**
 * Created by andy on 2017/7/27.
 */

public abstract class IReservePresenter extends BasePresenter<IReserveView,IReserveModel>{
    public abstract void getPackageHospitals();
    public abstract void onHospitalReady(List<HospitalBean> collection);
    public abstract void getPackage(int id);
    public abstract void OnPackageReady(List<PackageBean> collection);
    public abstract void ReserveNow(long StartTime,long EndTime,String name,String phoneNumber,int id);
}
