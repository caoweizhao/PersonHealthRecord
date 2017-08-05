package com.example.administrator.personhealthrecord.mvp.main;


import android.util.Log;

import com.example.administrator.personhealthrecord.bean.UserInfoBean;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017-7-17.
 */

public class MainModel extends AMainModel {

    MainService mService;

    public MainModel(AMainPresenter presenter) {
        super(presenter);
        mService = RetrofitUtil.getRetrofit().create(MainService.class);
    }

    @Override
    public void getAvatorUrl() {
        mService.getSelfInfo(Contract.cookie)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ResponseBody value) {
                        try {
                            JSONObject jsonObject;
                            jsonObject = new JSONObject(value.string());
                            if (jsonObject.get("status").equals("success")) {
                                Gson gson = new Gson();
                                UserInfoBean mUserInfoBean = gson.fromJson(jsonObject.get("object").toString(),
                                        UserInfoBean.class);
                                if (mPresenter != null) {
                                    mPresenter.onAvatorUrlReady(mUserInfoBean.getIconImage());
                                }
                            }
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("MainModel", "onError" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

}
