package com.example.administrator.personhealthrecord.mvp.register_and_login;

import com.example.administrator.personhealthrecord.bean.LoginBean;
import com.example.administrator.personhealthrecord.bean.RegisterBean;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.mvp.register_and_login.api.LoginApi;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by andy on 2017/7/24.
 */

class RegisterAndLoginModelImpl extends IRegisterAndLoginModel {
    RegisterAndLoginModelImpl(IRegisterAndLoginPresenter presenter) {
        super(presenter);
    }

    @Override
    void Login(String userName, String password, Observer<Response<LoginBean>> observer) {
        Retrofit retrofit = RetrofitUtil.getRetrofit();
        LoginApi api = retrofit.create(LoginApi.class);
        api.login(userName, password)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    void register(String userName, String password, Observer<RegisterBean> observer) {
        Retrofit retrofit = RetrofitUtil.getRetrofit();
        LoginApi api = retrofit.create(LoginApi.class);
        api.register(userName, password)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    void loginOUt(Observer<ResponseBody> observer) {
        Retrofit retrofit = RetrofitUtil.getRetrofit();
        LoginApi api = retrofit.create(LoginApi.class);
        api.loginOut(Contract.cookie)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
