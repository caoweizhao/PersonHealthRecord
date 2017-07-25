package com.example.administrator.personhealthrecord.mvp.log;

import com.example.administrator.personhealthrecord.bean.Loginbean;
import com.example.administrator.personhealthrecord.mvp.base.BasePresenter;

import io.reactivex.Observer;


/**
 * Created by andy on 2017/7/24.
 */

public abstract class ILoginPresenter extends BasePresenter<ILoginVIew,ILoginModle>{
    abstract void dologin( String username, String password);
}
