package com.example.administrator.personhealthrecord.mvp.chat;

import com.example.administrator.personhealthrecord.mvp.base.MvpActivity;

/**
 * Created by andy on 2017/7/24.
 */

public abstract class IChatVIew extends MvpActivity<IChatPresenter> {
    public abstract void getHelp();

    public abstract void getResponse(String question);

    public abstract void OnHelpMessageReady(String message);

    public abstract void OnSimpleMessageReady(String message);
}
