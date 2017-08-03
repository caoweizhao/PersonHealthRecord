package com.example.administrator.personhealthrecord.mvp.checkpage;

import com.example.administrator.personhealthrecord.bean.CheckBean;
import com.example.administrator.personhealthrecord.bean.ImageBean;
import com.example.administrator.personhealthrecord.mvp.base.BasePresenter;

import java.util.List;

/**
 * Created by Administrator on 2017-7-19.
 */

public abstract class ACheckPagePresenter extends BasePresenter<ACheckPageFragment, ACheckPageModel> {

    public abstract void onImagesReady(List<ImageBean> urls);

    public abstract void onDataReady(List<CheckBean> checkBeanList);

    public abstract void onRequestData();

}
