package com.example.administrator.personhealthrecord.mvp.reserve_order;

import com.example.administrator.personhealthrecord.bean.AppointmentBean;
import com.example.administrator.personhealthrecord.bean.ReserveOrderBean;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfHealthyOrderBean;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.mvp.reserve_order.api.ReserveOrderService;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by andy on 2017/8/1.
 */

class ReserveOrderModelImpl extends IReserveOrderModel {
    ReserveOrderModelImpl(IReserveOrderPresenter presenter) {
        super(presenter);
    }

    @Override
    public void getHealthyCheckList(Observer<ResultUtilOfHealthyOrderBean<ReserveOrderBean>> observer) {
        Retrofit client = RetrofitUtil.getRetrofit();
        ReserveOrderService service = client.create(ReserveOrderService.class);
        service.getHealthyCheckOrderList(Contract.cookie)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
    @Override
    public void getAppointmentList(Observer<ResultUtilOfHealthyOrderBean<AppointmentBean>> observer) {
        Retrofit client = RetrofitUtil.getRetrofit();
        ReserveOrderService service = client.create(ReserveOrderService.class);
        service.getAppointment(Contract.cookie)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
