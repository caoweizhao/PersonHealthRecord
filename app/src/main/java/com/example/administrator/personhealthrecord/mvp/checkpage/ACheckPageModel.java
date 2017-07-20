package com.example.administrator.personhealthrecord.mvp.checkpage;

import com.example.administrator.personhealthrecord.mvp.base.BaseModel;

/**
 * Created by Administrator on 2017-7-19.
 */

public abstract class ACheckPageModel extends BaseModel<ACheckPagePresenter> {

    public ACheckPageModel(ACheckPagePresenter presenter) {
        super(presenter);
    }

    public abstract void getImageRes();

    public abstract void getCheckItems();

}
