package com.example.administrator.personhealthrecord.mvp.chat;

import com.example.administrator.personhealthrecord.bean.AIResponseBean;
import com.example.administrator.personhealthrecord.bean.AIResponeHelpBean;
import com.example.administrator.personhealthrecord.mvp.chat.api.ChatService;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by andy on 2017/8/1.
 */

class ChatModelImpl extends IChatModel {
    ChatModelImpl(IChatPresenter presenter) {
        super(presenter);
    }

    @Override
    void getHelper(Observer<AIResponeHelpBean> observer) {
        Retrofit client=RetrofitUtil.getRetrofit();
        ChatService service=client.create(ChatService.class);
        service.getHelp()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    void getResponse(Observer<AIResponseBean> observer, String question) {
        Retrofit client=RetrofitUtil.getRetrofit();
        ChatService service=client.create(ChatService.class);
        service.getResponse(question)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
