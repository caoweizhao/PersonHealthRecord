package com.example.administrator.personhealthrecord.mvp.register_and_login;

import com.example.administrator.personhealthrecord.mvp.base.BasePresenter;


/**
 * Created by andy on 2017/7/24.
 */

public abstract class IRegisterAndLoginPresenter extends BasePresenter<ILoginVIew,IRegisterAndLoginModel>{
    abstract void doLogin(String username, String password);
    abstract void register(String username, String password);
    abstract void onDetach();
    abstract void logOut();
}
