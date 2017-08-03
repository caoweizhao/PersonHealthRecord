package com.example.administrator.personhealthrecord.mvp.reserveorder;

import com.example.administrator.personhealthrecord.bean.AppointmentBean;
import com.example.administrator.personhealthrecord.bean.ReserOrderBean;
import com.example.administrator.personhealthrecord.bean.ReserveBean;
import com.example.administrator.personhealthrecord.bean.ResultUitlOfReserve;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfHealthyOrderBean;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfPackageBean;
import com.example.administrator.personhealthrecord.mvp.base.BaseModel;

import io.reactivex.Observable;
import io.reactivex.Observer;


/**
 * Created by andy on 2017/7/27.
 */

public abstract class IReserveOrderModle extends BaseModel<IResreveOrderPresenter>{
    public IReserveOrderModle(IResreveOrderPresenter presenter) {
        super(presenter);
    }

    public abstract void getHealthyCheckList(Observer<ResultUtilOfHealthyOrderBean<ReserOrderBean>> observer);
    public abstract void getAppoitmentList(Observer<ResultUtilOfHealthyOrderBean<AppointmentBean>> observer);
}
