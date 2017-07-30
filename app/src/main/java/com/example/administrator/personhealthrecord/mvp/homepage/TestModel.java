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
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017-7-19.
 */

public class TestModel extends AHomePageModel {
    private HomePageService mHomePageService;

    public TestModel(AHomePagePresenter presenter) {
        super(presenter);
        mHomePageService = RetrofitUtil.getRetrofit().create(HomePageService.class);
    }

    @Override
    public void getImageRes() {
        List images = new ArrayList();
        images.add("http://img06.tooopen.com/images/20160921/tooopen_sy_179583447187.jpg");
        images.add("http://pics.sc.chinaz.com/files/pic/pic9/201508/apic14052.jpg");
        images.add("http://img02.tooopen.com/images/20160509/tooopen_sy_161967094653.jpg");
        images.add("http://pic.sc.chinaz.com/files/pic/pic9/201208/xpic6813.jpg");
        List titles = new ArrayList();
        titles.add("Title1");
        titles.add("Title2");
        titles.add("Title3");
        titles.add("Title4");
        mPresenter.onImagesReady(images);
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
                                mPresenter.onErrorHappened((String) jsonObject.get("status"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return Observable.create(new ObservableOnSubscribe<ExpertBean>() {
                            @Override
                            public void subscribe(ObservableEmitter<ExpertBean> e) throws Exception {

                            }
                        });
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
//        List<HospitalBean> hospitalBeens = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            HospitalBean hospitalBean = new HospitalBean();
//            hospitalBean.setName("中山大学第三附属医院（天河院区）");
//            hospitalBean.setAddress("广州市天河区天河路600号");
//            hospitalBean.setLevel("一级甲等");
//            hospitalBeens.add(hospitalBean);
//        }
//        mPresenter.onHospitalReady(hospitalBeens);
        /*mHomePageService = RetrofitUtil.getRetrofit().create(HomePageService.class);
        mHomePageService.getHospitals()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);*/
    }

    @Override
    public void saveToDB(List<HospitalBean> list) {
        /*List<HospitalBean> DBlist= DataSupport.findAll(HospitalBean.class);
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
