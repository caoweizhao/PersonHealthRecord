package com.example.administrator.personhealthrecord.mvp.reserve_order;

import com.example.administrator.personhealthrecord.bean.AppointmentBean;
import com.example.administrator.personhealthrecord.bean.ReserveOrderBean;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfHealthyOrderBean;
import com.example.administrator.personhealthrecord.mvp.base.BaseModel;

import io.reactivex.Observer;


/**
 * Created by andy on 2017/7/27.
 */

abstract class IReserveOrderModel extends BaseModel<IReserveOrderPresenter> {
    IReserveOrderModel(IReserveOrderPresenter presenter) {
        super(presenter);
    }

    public abstract void getHealthyCheckList(Observer<ResultUtilOfHealthyOrderBean<ReserveOrderBean>> observer);

    public abstract void getAppointmentList(Observer<ResultUtilOfHealthyOrderBean<AppointmentBean>> observer);
}
