package com.example.administrator.personhealthrecord.mvp.log;

import com.example.administrator.personhealthrecord.bean.Loginbean;
import com.example.administrator.personhealthrecord.mvp.base.BaseModel;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.Response;

/**
 * Created by andy on 2017/7/24.
 */

public abstract class ILoginModle extends BaseModel<ILoginPresenter>{
    public ILoginModle(ILoginPresenter presenter) {
        super(presenter);
    }
    abstract void Login(String usernem, String password, Observer<Response<Loginbean>> observer);
}
