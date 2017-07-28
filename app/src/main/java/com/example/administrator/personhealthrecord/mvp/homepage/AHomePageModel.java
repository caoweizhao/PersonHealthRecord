package com.example.administrator.personhealthrecord.mvp.homepage;

import com.example.administrator.personhealthrecord.bean.HospitalBean;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfHospitalList;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.mvp.base.BaseModel;

import java.util.List;

import io.reactivex.Observer;


/**
 * Created by Administrator on 2017-7-19.
 */

public abstract class AHomePageModel extends BaseModel<AHomePagePresenter> {



    public AHomePageModel(AHomePagePresenter presenter) {
        super(presenter);
    }

    public abstract void getImageRes();

    public abstract void getExperts(@Contract.ExpertType int type);

    public abstract void getHospitals(Observer<ResultUtilOfHospitalList> observer);

    public abstract void saveToDB(List<HospitalBean> list);

    public abstract List<HospitalBean> getBDlist();
}
