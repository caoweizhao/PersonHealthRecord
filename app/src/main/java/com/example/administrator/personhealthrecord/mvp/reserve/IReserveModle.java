package com.example.administrator.personhealthrecord.mvp.reserve;

import com.example.administrator.personhealthrecord.bean.ResultUtilOfHospitalList;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfPackageBean;
import com.example.administrator.personhealthrecord.mvp.base.BaseModel;

import io.reactivex.Observer;


/**
 * Created by andy on 2017/7/27.
 */

public abstract class IReserveModle extends BaseModel<IResrevePresenter>{
    public IReserveModle(IResrevePresenter presenter) {
        super(presenter);
    }

   public abstract void getPackageHospitals(Observer<ResultUtilOfHospitalList> observer);
    public abstract void getPackage(Observer<ResultUtilOfPackageBean> observer, int id);
}
