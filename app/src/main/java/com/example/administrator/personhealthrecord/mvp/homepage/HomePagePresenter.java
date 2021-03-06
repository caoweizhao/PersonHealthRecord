package com.example.administrator.personhealthrecord.mvp.homepage;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.administrator.personhealthrecord.bean.AdvertisementBean;
import com.example.administrator.personhealthrecord.bean.ExpertBean;
import com.example.administrator.personhealthrecord.bean.HospitalBean;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfHospitalList;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.util.ToastUtil;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017-7-19.
 */

class HomePagePresenter extends AHomePagePresenter {
    private static final String TAG = "HomePagePresenter";

    @Override
    public AHomePageModel createModel() {
        return new HomePageModel(this);
    }

    @Override
    public void onImagesReady(List<AdvertisementBean> urls) {
        mView.updateImages(urls);
    }

    @Override
    public void onExpertsReady(List<ExpertBean> expertBeanList) {
        mView.updateExperts(expertBeanList);
    }

    @Override
    public void onHospitalReady(List<HospitalBean> hospitalBeanList) {
        mView.initHospitals(hospitalBeanList);
    }

    @Override
    public void onRequestData() {
        mView.showLoading();
        mModel.getImageRes();
        mModel.getExperts(Contract.TYPE_ORTHOPEDICS);
        getHospitalLis();
    }

    @Override
    public void onSpinnerItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int type = Contract.TYPE_ORTHOPEDICS;
        switch (position) {
            case 0:
                type = Contract.TYPE_ORTHOPEDICS;
                break;
            case 1:
                type = Contract.TYPE_PEDIATRIC_SURGERY;
                break;
            case 2:
                type = Contract.TYPE_ENT;
                break;
            case 3:
                type = Contract.TYPE_DERMATOLOGY;
                break;
            default:
                break;
        }
        mModel.getExperts(type);
    }

    @Override
    public void getHospitalLis() {
        onHospitalReady(mModel.getDBList());//先从数据库取出列表然后在进行网络请求
        Observer<ResultUtilOfHospitalList> observer = new Observer<ResultUtilOfHospitalList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResultUtilOfHospitalList value) {
                if (value.getStatus().equals("success")) {
                    List<HospitalBean> list = value.getCollection();
                    int i;
                    for (i = 0; i < list.size(); i++) {
                        Log.d(TAG, "onNext: " + list.get(i).getName());
                    }
                    mView.updateHospitals(value.getCollection());
                    mModel.saveToDB(value.getCollection());
                } else {
                    ToastUtil.Toast(value.getMessage());
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {

            }
        };
        mModel.getHospitals(observer);
    }

    @Override
    public void onErrorHappened(String error) {
        mView.showMessage(error);
    }


}
