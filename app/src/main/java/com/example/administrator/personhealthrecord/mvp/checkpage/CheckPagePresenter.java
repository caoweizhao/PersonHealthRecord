package com.example.administrator.personhealthrecord.mvp.checkpage;

import com.example.administrator.personhealthrecord.bean.AbstractResultUtil;
import com.example.administrator.personhealthrecord.bean.CheckBean;
import com.example.administrator.personhealthrecord.bean.ImageBean;
import com.example.administrator.personhealthrecord.util.ToastUtil;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017-7-19.
 */

class CheckPagePresenter extends ACheckPagePresenter {

    @Override
    public ACheckPageModel createModel() {
        return new CheckPageModel(this);
    }

    @Override
    public void onImagesReady(List<ImageBean> urls) {
        mView.updateImages(urls);
    }

    @Override
    public void onDataReady(List<CheckBean> checkBeanList) {
        mView.dismissLoading();
        mView.updateCheckItems(checkBeanList);
    }

    @Override
    public void onRequestData() {
        Observer<AbstractResultUtil<ImageBean>> ImageObserver = new Observer<AbstractResultUtil<ImageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AbstractResultUtil<ImageBean> value) {
                if (value.status.equals("success")) {
                    onImagesReady(value.getCollection());
                } else {
                    ToastUtil.Toast(value.message);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        mModel.getImageRes(ImageObserver);
    }

}
