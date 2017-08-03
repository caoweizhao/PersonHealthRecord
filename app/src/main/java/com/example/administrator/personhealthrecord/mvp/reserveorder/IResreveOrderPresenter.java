package com.example.administrator.personhealthrecord.mvp.reserveorder;

import com.example.administrator.personhealthrecord.bean.HospitalBean;
import com.example.administrator.personhealthrecord.bean.PackageBean;
import com.example.administrator.personhealthrecord.bean.ResultUitlOfReserve;
import com.example.administrator.personhealthrecord.mvp.base.BasePresenter;

import java.util.List;

/**
 * Created by andy on 2017/7/27.
 */

public abstract class IResreveOrderPresenter extends BasePresenter<IReserveOrderView,IReserveOrderModle>{
    public abstract void getHealthCheckeList();
    public abstract void getAppoitmentList();
}
