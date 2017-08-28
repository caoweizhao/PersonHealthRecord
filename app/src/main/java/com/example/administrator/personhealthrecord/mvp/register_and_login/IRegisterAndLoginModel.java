package com.example.administrator.personhealthrecord.mvp.register_and_login;

import com.example.administrator.personhealthrecord.bean.LoginBean;
import com.example.administrator.personhealthrecord.bean.RegisterBean;
import com.example.administrator.personhealthrecord.mvp.base.BaseModel;

import io.reactivex.Observer;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by andy on 2017/7/24.
 */

abstract class IRegisterAndLoginModel extends BaseModel<IRegisterAndLoginPresenter> {
    IRegisterAndLoginModel(IRegisterAndLoginPresenter presenter) {
        super(presenter);
    }

    abstract void Login(String userName, String password, Observer<Response<LoginBean>> observer);

    abstract void register(String userName, String password, Observer<RegisterBean> observer);

    abstract void loginOUt(Observer<ResponseBody> observer);
}
