package com.example.administrator.personhealthrecord.mvp.registandlogin;

import com.example.administrator.personhealthrecord.mvp.base.BasePresenter;


/**
 * Created by andy on 2017/7/24.
 */

public abstract class IRegistAndLoginPresenter extends BasePresenter<ILoginVIew,IRegistAndLoginModle>{
    abstract void dologin( String username, String password);
    abstract void regist( String username, String password);
}
