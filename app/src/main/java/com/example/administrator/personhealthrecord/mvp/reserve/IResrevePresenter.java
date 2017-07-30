package com.example.administrator.personhealthrecord.mvp.reserve;

import com.example.administrator.personhealthrecord.bean.HospitalBean;
import com.example.administrator.personhealthrecord.bean.PackageBean;
import com.example.administrator.personhealthrecord.mvp.base.BasePresenter;

import java.util.List;

/**
 * Created by andy on 2017/7/27.
 */

public abstract class IResrevePresenter extends BasePresenter<IReserveView,IReserveModle>{
    public abstract void getPackageHospitals();
    public abstract void OnhospitalReady(List<HospitalBean> collection);
    public abstract void getPackage(int id);
    public abstract void OnPackageReadey(List<PackageBean> collection);
    public abstract void ReserveNow(long StartTime,long EndTime,String name,String phoneNumber,int id);
}
