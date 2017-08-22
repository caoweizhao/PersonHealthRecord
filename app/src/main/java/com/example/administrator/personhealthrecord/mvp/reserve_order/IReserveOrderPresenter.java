package com.example.administrator.personhealthrecord.mvp.reserve_order;

import com.example.administrator.personhealthrecord.mvp.base.BasePresenter;

/**
 * Created by andy on 2017/7/27.
 */

public abstract class IReserveOrderPresenter extends BasePresenter<IReserveOrderView,IReserveOrderModel>{
    public abstract void getHealthCheckedList();
    public abstract void getAppointmentList();
}
