package com.example.administrator.personhealthrecord.mvp.homepage;

import com.example.administrator.personhealthrecord.bean.ExpertBean;
import com.example.administrator.personhealthrecord.bean.HospitalBean;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfHospitalList;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.mvp.homepage.api.HomePageService;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
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
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<List<String>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<String>> value) {
                        mPresenter.onImagesReady(value.response().body());
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
    public void getExperts(@Contract.ExpertType int type) {
        final List<ExpertBean> experts = new ArrayList<>();
        mHomePageService.getExperts(Contract.ExpertType[type])
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<ResponseBody, ObservableSource<ExpertBean>>() {
                    @Override
                    public ObservableSource<ExpertBean> apply(ResponseBody body) throws Exception {
                        try {
                            JSONObject jsonObject = new JSONObject(body.string());
                            if (jsonObject.get("status").equals("success")) {
                                Gson gson = new Gson();
                                List<ExpertBean> expertBeanList = gson.fromJson(
                                        jsonObject.get("collection").toString(),
                                        new TypeToken<List<ExpertBean>>() {
                                        }.getType());
                                return Observable.fromIterable(expertBeanList);
                            } else {
                                mPresenter.onErrorHappened((String) jsonObject.get("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return Observable.fromIterable(Collections.<ExpertBean>emptyList());
                    }
                })
                .take(5)
                .subscribe(new Observer<ExpertBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ExpertBean value) {
                        experts.add(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mPresenter.onErrorHappened(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        mPresenter.onExpertsReady(experts);
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
       /* List<HospitalBean> DBlist= DataSupport.findAll(HospitalBean.class);
        for(HospitalBean bean:list)
        {
            if(!DBlist.contains(bean))
                bean.save();
        }*/
    }

    @Override
    public List<HospitalBean> getBDlist() {
        //return DataSupport.findAll(HospitalBean.class);
        return null;
    }
}
