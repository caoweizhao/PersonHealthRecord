package com.example.administrator.personhealthrecord.mvp.chat;

import com.example.administrator.personhealthrecord.bean.AIResponeBean;
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

public class ChatModleImpl extends IChatModle{
    public ChatModleImpl(IChatPresenter presenter) {
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
    void getRespone(Observer<AIResponeBean> observer, String question) {
        Retrofit client=RetrofitUtil.getRetrofit();
        ChatService service=client.create(ChatService.class);
        service.getRespone(question)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
