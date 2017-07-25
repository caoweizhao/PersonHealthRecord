package com.example.administrator.personhealthrecord.mvp.log;

import android.util.Log;

import com.example.administrator.personhealthrecord.bean.Loginbean;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;
import com.example.administrator.personhealthrecord.util.ToastUitl;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by andy on 2017/7/24.
 */

public class LoginPresenterImpl extends ILoginPresenter{
    private static final String TAG="LoginPresenterImpl";
    private ILoginModle modle;
    public LoginPresenterImpl(ILoginVIew loginVIew)
    {
        this.mView=loginVIew;
    }

    @Override
    public ILoginModle createModel() {
        mModel=new LoginModleImpl(this);
        return mModel;
    }
    @Override
    void dologin(String username, String password) {
        Log.d(TAG, "dologin: ");
        Observer<Response<Loginbean>> observer=new Observer<Response<Loginbean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<Loginbean> value) {
                Loginbean bean=(Loginbean)value.body();
                Log.d(TAG, "onNext: "+((Loginbean)value.body()).getMessage());


                if(bean.getStatus().equals("fail"))
                    ToastUitl.Toast(bean.getMessage());
                else
                {
                    String cookie=value.headers().get("Set-Cookie").split(";")[0];
                    Contract.cookie=cookie;
                    Log.d(TAG, "onNext: "+cookie.toString());
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e.toString());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };

        mModel.Login(username,password,observer);
    }
}
