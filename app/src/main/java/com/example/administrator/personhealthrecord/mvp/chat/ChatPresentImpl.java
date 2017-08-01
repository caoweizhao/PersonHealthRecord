package com.example.administrator.personhealthrecord.mvp.chat;


import android.util.Log;

import com.example.administrator.personhealthrecord.bean.AIResponeBean;
import com.example.administrator.personhealthrecord.bean.AIResponeHelpBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by andy on 2017/8/1.
 */

public class ChatPresentImpl extends IChatPresenter{
    private static final String TAG="ChatPresentImpl";
    @Override
    void getHelpe() {
        Observer<AIResponeHelpBean> observer=new Observer<AIResponeHelpBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AIResponeHelpBean value) {
                Log.d(TAG, "onNext: "+value.getObject().getAnwser());
                mView.OnHelpMessageReady(value.getObject().getAnwser());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        mModel.getHelper(observer);
    }

    @Override
    void getResone(String question) {
        Observer<AIResponeBean> observer=new Observer<AIResponeBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AIResponeBean value) {
                Log.d(TAG, "onNext: "+value.getObject());
                mView.OnSimpleMessageReady(value.getObject());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        mModel.getRespone(observer,question);
    }

    @Override
    public IChatModle createModel() {
        return new ChatModleImpl(this);
    }
}
