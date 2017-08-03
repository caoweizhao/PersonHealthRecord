package com.example.administrator.personhealthrecord.mvp.chat.api;

import com.example.administrator.personhealthrecord.bean.AIResponeBean;
import com.example.administrator.personhealthrecord.bean.AIResponeHelpBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by andy on 2017/8/1.
 */

public interface ChatService {
    @GET("ai_chat/first_message")
    Observable<AIResponeHelpBean> getHelp();

    @FormUrlEncoded
    @POST("ai_chat/question")
    Observable<AIResponeBean> getRespone(@Field("question")String question);

}
