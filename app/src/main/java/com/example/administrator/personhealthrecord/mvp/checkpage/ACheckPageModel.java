package com.example.administrator.personhealthrecord.mvp.checkpage;

import com.example.administrator.personhealthrecord.bean.AbstractReserveBean;
import com.example.administrator.personhealthrecord.bean.AbstractResulUitl;
import com.example.administrator.personhealthrecord.bean.ImageBean;
import com.example.administrator.personhealthrecord.mvp.base.BaseModel;

import io.reactivex.Observer;

/**
 * Created by Administrator on 2017-7-19.
 */

public abstract class ACheckPageModel extends BaseModel<ACheckPagePresenter> {

    public ACheckPageModel(ACheckPagePresenter presenter) {
        super(presenter);
    }

    public abstract void getImageRes(Observer<AbstractResulUitl<ImageBean>> observer);

    public abstract void getCheckItems();

}
