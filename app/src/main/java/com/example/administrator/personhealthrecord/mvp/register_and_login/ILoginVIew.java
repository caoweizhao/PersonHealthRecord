package com.example.administrator.personhealthrecord.mvp.register_and_login;

import com.example.administrator.personhealthrecord.mvp.base.MvpActivity;

/**
 * Created by andy on 2017/7/24.
 */

public abstract class ILoginVIew extends MvpActivity<IRegisterAndLoginPresenter>{
    abstract void doLogin();
    public abstract void register();

    public void finishActivity() {
    }
    public abstract void showSnackBar(String string);
}
