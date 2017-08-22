package com.example.administrator.personhealthrecord.mvp.reserve;

import com.example.administrator.personhealthrecord.bean.ReserveBean;
import com.example.administrator.personhealthrecord.bean.ResultUitlOfReserve;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfHospitalList;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfPackageBean;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.mvp.reserve.api.HealthReserveService;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;

import java.sql.Timestamp;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by andy on 2017/7/27.
 */

class ReserveModelImpl extends IReserveModel {
    ReserveModelImpl(IReservePresenter presenter) {
        super(presenter);
    }

    @Override
    public void getPackageHospitals(Observer<ResultUtilOfHospitalList> observer) {
        Retrofit retrofit = RetrofitUtil.getRetrofit();
        HealthReserveService api = retrofit.create(HealthReserveService.class);
        if (Contract.IS_DISCOUNT) {
            api.getDiscountPackageHospitalList()
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        } else {
            api.getOwnPackageHospitalList()
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }

    }

    @Override
    public void getPackage(Observer<ResultUtilOfPackageBean> observer, int id) {
        Retrofit retrofit = RetrofitUtil.getRetrofit();
        HealthReserveService api = retrofit.create(HealthReserveService.class);
        if (Contract.IS_DISCOUNT) {
            api.getHospitalDiscountPackage(id)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        } else {
            api.getHospitalPackage(id)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }

    }

    @Override
    public void ReserveNow(Observer<ResultUitlOfReserve> observer, ReserveBean reserveBean) {
        Retrofit retrofit = RetrofitUtil.getRetrofit();
        HealthReserveService api = retrofit.create(HealthReserveService.class);
        api.ReserveNow(Contract.cookie, new Timestamp(reserveBean.getStartTime()), new Timestamp(reserveBean.getEndTime()), reserveBean.getName(), reserveBean.getPhoneNumber(), reserveBean.getId())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
