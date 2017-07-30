package com.example.administrator.personhealthrecord.mvp.homepage;

import com.example.administrator.personhealthrecord.bean.ExpertBean;
import com.example.administrator.personhealthrecord.bean.HospitalBean;
import com.example.administrator.personhealthrecord.mvp.base.MvpFragment;

import java.util.List;

/**
 * Created by Administrator on 2017-7-19.
 */

public abstract class AHomePageFragment extends MvpFragment<AHomePagePresenter> {

    public abstract void updateImages(List<String> urls);
    public abstract void updateExperts(List<ExpertBean> expertBeenF);
    public abstract void InitHospitals(List<HospitalBean> hospitalBeanList);
    public abstract void updateHospitals(List<HospitalBean> hospitalBeanList);

}
