package com.example.administrator.personhealthrecord.mvp.registandlogin;

import com.example.administrator.personhealthrecord.bean.Loginbean;
import com.example.administrator.personhealthrecord.bean.RegistBean;
import com.example.administrator.personhealthrecord.mvp.registandlogin.api.LoginApi;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by andy on 2017/7/24.
 */

public class RegistAndyLoginModleImpl extends IRegistAndLoginModle {
    public RegistAndyLoginModleImpl(IRegistAndLoginPresenter presenter) {
        super(presenter);
    }

    @Override
    void Login(String usernem, String password, Observer<Response<Loginbean>> observer) {
       Retrofit retrofit= RetrofitUtil.getRetrofit();
        LoginApi api=retrofit.create(LoginApi.class);
        api.login(usernem,password)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    void regist(String usernem, String password, Observer<RegistBean> observer) {
        Retrofit retrofit= RetrofitUtil.getRetrofit();
        LoginApi api=retrofit.create(LoginApi.class);
        api.regist(usernem,password)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
