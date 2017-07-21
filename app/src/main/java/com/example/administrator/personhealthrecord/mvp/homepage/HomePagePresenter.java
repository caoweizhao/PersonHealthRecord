package com.example.administrator.personhealthrecord.mvp.homepage;

import android.view.View;
import android.widget.AdapterView;

import com.example.administrator.personhealthrecord.bean.ExpertBean;
import com.example.administrator.personhealthrecord.bean.HospitalBean;
import com.example.administrator.personhealthrecord.contract.Contract;

import java.util.List;

/**
 * Created by Administrator on 2017-7-19.
 */

public class HomePagePresenter extends AHomePagePresenter {

    @Override
    public AHomePageModel createModel() {
        // TODO: 2017-7-19
        //return new CheckPageModel(this);
        return new TestModel(this);
    }

    @Override
    public void onImagesReady(List<String> urls) {
        mView.updateImages(urls);
    }

    @Override
    public void onExpertsReady(List<ExpertBean> expertBeanList) {
        mView.updateExperts(expertBeanList);
    }

    @Override
    public void onHospitalReady(List<HospitalBean> hospitalBeanList) {
        mView.updateHospitals(hospitalBeanList);
    }

    @Override
    public void onRequestData() {
        mView.showLoading();
        mModel.getImageRes();
        mModel.getExperts(Contract.TYPE_CARDIOLOGY);
        mModel.getHospitals();
    }

    @Override
    public void onSpinnerItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int type = Contract.TYPE_CARDIOLOGY;
        switch (position) {
            case 0:
                type = Contract.TYPE_CARDIOLOGY;
                break;
            case 1:
                type = Contract.TYPE_INTERNAL_MEDICINE;
                break;
            case 2:
                type = Contract.TYPE_SURGICAL;
                break;
            case 3:
                type = Contract.TYPE_DERMATOLOGY;
                break;
            default:
                break;
        }
        mModel.getExperts(type);
    }


}
