package com.example.administrator.personhealthrecord.mvp.checkpage;

import com.example.administrator.personhealthrecord.bean.AbstractResulUitl;
import com.example.administrator.personhealthrecord.bean.CheckBean;
import com.example.administrator.personhealthrecord.bean.ImageBean;
import com.example.administrator.personhealthrecord.util.ToastUitl;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017-7-19.
 */

public class CheckPagePresenter extends ACheckPagePresenter {

    @Override
    public ACheckPageModel createModel() {
        // TODO: 2017-7-19
        //return new CheckPageModel(this);
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
        Observer<AbstractResulUitl<ImageBean>> Imageobserver=new Observer<AbstractResulUitl<ImageBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AbstractResulUitl<ImageBean> value) {
                if(value.status.equals("success"))
                {
                    onImagesReady(value.getCollection());
                }else
                {
                    ToastUitl.Toast(value.message);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
//        mView.showLoading();
        mModel.getImageRes(Imageobserver);
//        mModel.getCheckItems();
    }


}
