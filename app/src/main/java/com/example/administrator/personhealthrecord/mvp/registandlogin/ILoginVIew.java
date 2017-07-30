package com.example.administrator.personhealthrecord.mvp.registandlogin;

import com.example.administrator.personhealthrecord.mvp.base.MvpActivity;

/**
 * Created by andy on 2017/7/24.
 */

public abstract class ILoginVIew extends MvpActivity<IRegistAndLoginPresenter>{
    abstract void dologin();
    public abstract void regist();

    public void finishAcitvity() {
    }
}
