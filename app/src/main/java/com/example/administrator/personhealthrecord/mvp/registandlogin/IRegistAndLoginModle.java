package com.example.administrator.personhealthrecord.mvp.registandlogin;

import com.example.administrator.personhealthrecord.bean.Loginbean;
import com.example.administrator.personhealthrecord.bean.RegistBean;
import com.example.administrator.personhealthrecord.mvp.base.BaseModel;

import io.reactivex.Observer;
import retrofit2.Response;

/**
 * Created by andy on 2017/7/24.
 */

public abstract class IRegistAndLoginModle extends BaseModel<IRegistAndLoginPresenter>{
    public IRegistAndLoginModle(IRegistAndLoginPresenter presenter) {
        super(presenter);
    }
    abstract void Login(String usernem, String password, Observer<Response<Loginbean>> observer);
    abstract void regist(String usernem, String password, Observer<RegistBean> observer);
}
