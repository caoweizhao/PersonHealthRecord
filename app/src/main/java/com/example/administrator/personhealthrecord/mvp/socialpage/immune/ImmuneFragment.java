package com.example.administrator.personhealthrecord.mvp.socialpage.immune;


import android.support.v4.app.Fragment;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.ImmuneBean;
import com.example.administrator.personhealthrecord.mvp.socialpage.SocialPageBaseFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImmuneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImmuneFragment extends SocialPageBaseFragment<ImmuneBean, ImmuneService> {

    private List<Disposable> mDisposables = new ArrayList<>();
    private int offset = 0;

    public ImmuneFragment() {
        // Required empty public constructor
    }

    public static ImmuneFragment newInstance() {
        return new ImmuneFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.social_child_fragment;
    }

    /**
     * 获取最新数据
     */
    @Override
    protected void refreshData() {
        mSwipeRefreshLayout.setRefreshing(true);
        mService.getImmunesInfo()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .map(new Function<ResponseBody, List<ImmuneBean>>() {
                    @Override
                    public List<ImmuneBean> apply(ResponseBody body) throws Exception {
                        try {
                            JSONObject jsonObject = new JSONObject(body.string());
                            if (jsonObject.get("status").equals("success")) {
                                Gson gson = new Gson();
                                List<ImmuneBean> networkList = gson.fromJson(
                                        jsonObject.get("collection").toString(),
                                        new TypeToken<List<ImmuneBean>>() {
                                        }.getType());

                                List<ImmuneBean> localList = DataSupport.findAll(ImmuneBean.class);
                                List<ImmuneBean> resultList = new ArrayList<ImmuneBean>();
                                for (ImmuneBean immuneBean : networkList) {
                                    if (!localList.contains(immuneBean)) {
                                        resultList.add(immuneBean);
                                    }
                                }
                                DataSupport.saveAll(resultList);
                                return resultList;
                            }
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                        return Collections.emptyList();
                    }
                })
                .subscribe(new Observer<List<ImmuneBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposables.add(d);
                    }

                    @Override
                    public void onNext(List<ImmuneBean> value) {
                        refreshDataDone(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showMessage(e.getMessage());
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onComplete() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    /**
     * 获取更多数据
     */
    @Override
    protected void loadMoreData() {
        List<ImmuneBean> localList = DataSupport
                .limit(20)
                .offset(offset)
                .find(ImmuneBean.class);
        if(localList.size()==0){
            mAdapter.loadMoreEnd(true);
            showMessage("没有更多数据啦！");
            return ;
        }
        List<ImmuneBean> resultList = new ArrayList<>();
        List<ImmuneBean> curList = mAdapter.getData();
        for (ImmuneBean immuneBean : localList) {
            if (!curList.contains(immuneBean)) {
                resultList.add(immuneBean);
            }
        }
        offset += localList.size();
        loadMoreDataDone(resultList);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (Disposable disposable : mDisposables) {
            if (disposable != null) {
                disposable.dispose();
            }
        }
    }

    @Override
    protected void initData() {
        Observable<List<ImmuneBean>> networkObservable = mService.getImmunesInfo()
                .subscribeOn(Schedulers.newThread())
                .map(new Function<ResponseBody, List<ImmuneBean>>() {
                    @Override
                    public List<ImmuneBean> apply(ResponseBody body) throws Exception {
                        try {
                            JSONObject jsonObject = new JSONObject(body.string());
                            if (jsonObject.get("status").equals("success")) {
                                Gson gson = new Gson();
                                List<ImmuneBean> networkList = gson.fromJson(
                                        jsonObject.get("collection").toString(),
                                        new TypeToken<List<ImmuneBean>>() {
                                        }.getType());
                                DataSupport.saveAll(networkList);
                                return networkList;
                            }
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                        return Collections.emptyList();
                    }
                });


        Observable<List<ImmuneBean>> localObservable = Observable.create(new ObservableOnSubscribe<List<ImmuneBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ImmuneBean>> e) throws Exception {
                List<ImmuneBean> localMedicineList = DataSupport.limit(20)
                        .offset(offset)
                        .find(ImmuneBean.class);
                if (localMedicineList.size() == 0) {
                    e.onComplete();
                } else {
                    offset += localMedicineList.size();
                    e.onNext(localMedicineList);
                }
            }
        });

        Observable.concat(localObservable, networkObservable)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ImmuneBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposables.add(d);
                    }

                    @Override
                    public void onNext(List<ImmuneBean> medicineBeanList) {
                        initDataDone(medicineBeanList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        showMessage(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        mSwipeRefreshLayout.setRefreshing(false);
                        showMessage(getString(R.string.success_msg));
                    }
                });
    }
}
