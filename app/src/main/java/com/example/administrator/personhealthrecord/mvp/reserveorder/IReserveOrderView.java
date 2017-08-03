package com.example.administrator.personhealthrecord.mvp.reserveorder;

import com.example.administrator.personhealthrecord.base.BaseFragment;
import com.example.administrator.personhealthrecord.bean.ResultUitlOfReserve;
import com.example.administrator.personhealthrecord.mvp.base.IView;
import com.example.administrator.personhealthrecord.mvp.base.MvpActivity;
import com.example.administrator.personhealthrecord.mvp.base.MvpFragment;

import java.util.List;

/**
 * Created by andy on 2017/7/27.
 */

public abstract class IReserveOrderView extends MvpFragment<IResreveOrderPresenter> {
    public abstract void getList();
}
