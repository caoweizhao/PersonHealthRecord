package com.example.administrator.personhealthrecord.mvp.reserveorder;

import com.example.administrator.personhealthrecord.base.BaseFragment;
import com.example.administrator.personhealthrecord.bean.ResultUitlOfReserve;
import com.example.administrator.personhealthrecord.mvp.base.IView;
import com.example.administrator.personhealthrecord.mvp.base.MvpActivity;

import java.util.List;

/**
 * Created by andy on 2017/7/27.
 */

public abstract class IReserveOrderView extends MvpActivity<IResreveOrderPresenter> {
    public abstract void getHealthCheckeList();
    public abstract void updataHealthCheckeList(List<ResultUitlOfReserve> list);
}
