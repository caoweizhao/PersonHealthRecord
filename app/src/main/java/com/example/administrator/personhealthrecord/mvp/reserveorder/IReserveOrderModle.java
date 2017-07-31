package com.example.administrator.personhealthrecord.mvp.reserveorder;

import com.example.administrator.personhealthrecord.bean.ReserveBean;
import com.example.administrator.personhealthrecord.bean.ResultUitlOfReserve;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfPackageBean;
import com.example.administrator.personhealthrecord.mvp.base.BaseModel;

import io.reactivex.Observer;


/**
 * Created by andy on 2017/7/27.
 */

public abstract class IReserveOrderModle extends BaseModel<IResreveOrderPresenter>{
    public IReserveOrderModle(IResreveOrderPresenter presenter) {
        super(presenter);
    }

    public abstract void getHealthyCheckList(Observer<ResultUitlOfReserve> observer);
}
