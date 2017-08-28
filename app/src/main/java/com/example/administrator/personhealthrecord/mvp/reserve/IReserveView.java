package com.example.administrator.personhealthrecord.mvp.reserve;

import com.example.administrator.personhealthrecord.bean.HospitalBean;
import com.example.administrator.personhealthrecord.bean.PackageBean;
import com.example.administrator.personhealthrecord.mvp.base.MvpActivity;

import java.util.List;

/**
 * Created by andy on 2017/7/27.
 */

public abstract class IReserveView extends MvpActivity<IReservePresenter> {
    public abstract void getPackageHospitals();

    public abstract void updateHospitals(List<HospitalBean> list);

    public abstract void getPackage(int id);

    public abstract void updatePackages(List<PackageBean> list);
}
