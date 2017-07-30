package com.example.administrator.personhealthrecord.mvp.registandlogin;

import android.util.Log;

import com.example.administrator.personhealthrecord.bean.Loginbean;
import com.example.administrator.personhealthrecord.bean.RegistBean;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.util.ToastUitl;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

/**
 * Created by andy on 2017/7/24.
 */

public class RegistAndLoginPresenterImpl extends IRegistAndLoginPresenter {
    private static final String TAG="RegistAndLoginPresenter";
    private IRegistAndLoginModle modle;
    public RegistAndLoginPresenterImpl(ILoginVIew loginVIew)
    {
        this.mView=loginVIew;
    }

    @Override
    public IRegistAndLoginModle createModel() {
        mModel=new RegistAndyLoginModleImpl(this);
        return mModel;
    }
    @Override
    void dologin(final String username, final String password) {
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
                {
                    ToastUitl.Toast(bean.getMessage());
                }
                else
                {
                    ToastUitl.Toast(bean.getMessage());
                    String cookie=value.headers().get("Set-Cookie").split(";")[0];
                    Contract.cookie=cookie;
                    Contract.IsLogin=Contract.Login;
                    Log.d(TAG, "onNext: "+cookie.toString());
                    ((LoginActivity)mView).SetAcount(username,password);
                    mView.finishAcitvity();
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

    @Override
    void regist(String username, String password) {
        Observer<RegistBean> observer=new Observer<RegistBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RegistBean value) {
                Log.d(TAG, "onNext: "+value.getMessage());

                if(value.getStatus().equals("success"))
                {
                    ToastUitl.Toast(value.getMessage());
                    mView.finish();
                }
                else
                {
                    ToastUitl.Toast(value.getMessage());
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

        mModel.regist(username,password,observer);
    }
}
