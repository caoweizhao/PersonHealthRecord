package com.example.administrator.personhealthrecord.mvp.reserve_order;

import com.example.administrator.personhealthrecord.mvp.base.MvpFragment;

/**
 * Created by andy on 2017/7/27.
 */

public abstract class IReserveOrderView extends MvpFragment<IReserveOrderPresenter> {
    public abstract void getList();
}
