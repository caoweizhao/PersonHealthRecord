package com.example.administrator.personhealthrecord.mvp.homepage;

import com.example.administrator.personhealthrecord.bean.ExpertBean;
import com.example.administrator.personhealthrecord.bean.HospitalBean;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfHospitalList;
import com.example.administrator.personhealthrecord.mvp.homepage.api.HomePageService;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;

import org.litepal.crud.DataSupport;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.Result;

/**
 * Created by Administrator on 2017-7-19.
 */

public class HomePageModel extends AHomePageModel {

    private HomePageService mHomePageService;

    public HomePageModel(AHomePagePresenter presenter) {
        super(presenter);
        mHomePageService = RetrofitUtil.getRetrofit().create(HomePageService.class);
    }

    @Override
    public void getImageRes() {
        mHomePageService.getImagesUrl()
                .observeOn(Schedulers.newThread())
                .subscribe(new Observer<Result<List<String>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<String>> value) {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getExperts(int type) {
        mHomePageService.getExperts()
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<List<ExpertBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ExpertBean> value) {
                        mPresenter.onExpertsReady(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getHospitals(Observer<ResultUtilOfHospitalList> observer) {
        mHomePageService.getHospitals()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void saveToDB(List<HospitalBean> list) {
        List<HospitalBean> DBlist= DataSupport.findAll(HospitalBean.class);
        for(HospitalBean bean:list)
        {
            if(!DBlist.contains(bean))
                bean.save();
        }
    }

    @Override
    public List<HospitalBean> getBDlist() {
        return DataSupport.findAll(HospitalBean.class);
    }
}
