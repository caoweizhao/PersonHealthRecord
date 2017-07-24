package com.example.administrator.personhealthrecord.mvp.socialpage.medical;

import com.example.administrator.personhealthrecord.mvp.base.BaseModel;

/**
 * Created by Administrator on 2017-7-19.
 */

public abstract class AMedicalModel extends BaseModel<AMedicalPresenter> {


    public AMedicalModel(AMedicalPresenter presenter) {
        super(presenter);
    }

    public abstract void getMedicineInfos();

}
