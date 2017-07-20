package com.example.administrator.personhealthrecord.mvp.checkpage;

import com.example.administrator.personhealthrecord.bean.CheckBean;
import com.example.administrator.personhealthrecord.mvp.checkpage.api.CheckPageService;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017-7-19.
 */

public class CheckPageModel extends ACheckPageModel {

    private CheckPageService mCheckPageService;

    public CheckPageModel(ACheckPagePresenter presenter) {
        super(presenter);
        mCheckPageService = RetrofitUtil.getRetrofit().create(CheckPageService.class);
    }

    @Override
    public void getImageRes() {
        mCheckPageService.getImagesUrl()
                .observeOn(Schedulers.newThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> value) {
                        mPresenter.onImagesReady(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void getCheckItems() {
        mCheckPageService.getCheckItems()
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<List<CheckBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<CheckBean> value) {
                        mPresenter.onDataReady(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
