package com.example.administrator.personhealthrecord.mvp.register_and_login;

import com.example.administrator.personhealthrecord.bean.LoginBean;
import com.example.administrator.personhealthrecord.bean.RegisterBean;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.util.ToastUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by andy on 2017/7/24.
 */

class RegisterAndLoginPresenterImpl extends IRegisterAndLoginPresenter {
    private static final String TAG = "RegisterAndLoginPresenter";
    private Disposable mDisposable;

    RegisterAndLoginPresenterImpl(ILoginVIew loginVIew) {
        this.mView = loginVIew;
    }

    @Override
    public IRegisterAndLoginModel createModel() {
        mModel = new RegisterAndLoginModelImpl(this);
        return mModel;
    }

    @Override
    void doLogin(final String username, final String password) {
        Observer<Response<LoginBean>> observer = new Observer<Response<LoginBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(Response<LoginBean> value) {
                LoginBean bean = value.body();
                if (bean.getStatus().equals("fail")) {
                    ((LoginActivity) mView).loginFail(value.body().getMessage());
                } else {
                    String cookie = value.headers().get("Set-Cookie").split(";")[0];
                    Contract.cookie = cookie;
                    Contract.IsLogin = Contract.Login;
                    ((LoginActivity) mView).setAccount(username, password);
                    mView.finishActivity();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView != null) {
                    mView.showSnackBar(e.toString());
                    ((LoginActivity) mView).loginFail("登录失败！");
                }
            }

            @Override
            public void onComplete() {
            }
        };

        mModel.Login(username, password, observer);
    }

    @Override
    void register(String username, String password) {
        Observer<RegisterBean> observer = new Observer<RegisterBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RegisterBean value) {

                if (value.getStatus().equals("success")) {
                    ToastUtil.Toast(value.getMessage());
                    mView.finish();
                } else {
                    ToastUtil.Toast(value.getMessage());
                }

            }

            @Override
            public void onError(Throwable e) {
                mView.showSnackBar(e.toString());
            }

            @Override
            public void onComplete() {
            }
        };

        mModel.register(username, password, observer);
    }

    @Override
    void onDetach() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    @Override
    void logOut() {
        Observer<ResponseBody> observer = new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody value) {
                Contract.IsLogin = Contract.UnLogin;
                Contract.cookie = null;
                ((LoginActivity) mView).onLoginDown();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        mModel.loginOUt(observer);
    }

}
