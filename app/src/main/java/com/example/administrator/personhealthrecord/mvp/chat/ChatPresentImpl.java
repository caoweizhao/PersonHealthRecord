package com.example.administrator.personhealthrecord.mvp.chat;


import android.util.Log;

import com.example.administrator.personhealthrecord.bean.AIResponseBean;
import com.example.administrator.personhealthrecord.bean.AIResponeHelpBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by andy on 2017/8/1.
 */

public class ChatPresentImpl extends IChatPresenter{
    private static final String TAG="ChatPresentImpl";

    /**
     * 获取帮助
     */
    @Override
    void getHelp() {
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

    /**
     * 根据问题返回答案
     * @param question
     */
    @Override
    void getResponse(String question) {
        Observer<AIResponseBean> observer=new Observer<AIResponseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AIResponseBean value) {
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
        mModel.getResponse(observer,question);
    }

    @Override
    public IChatModel createModel() {
        return new ChatModelImpl(this);
    }
}
