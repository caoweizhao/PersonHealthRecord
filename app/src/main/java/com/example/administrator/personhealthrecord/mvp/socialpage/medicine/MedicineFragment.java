package com.example.administrator.personhealthrecord.mvp.socialpage.medicine;


import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.MedicineBean;
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
 * 第一次进入先从数据库查找数据，若数据库有数据，则直接显示；若数据库无数据，则从网络获取数据，并且进行保存
 */
public class MedicineFragment extends SocialPageBaseFragment<MedicineBean, MedicineService> {

    private List<Disposable> mDisposables = new ArrayList<>();

    public MedicineFragment() {
        // Required empty public constructor
    }

    public static MedicineFragment newInstance() {
        return new MedicineFragment();
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
        mService.getMedicineInfos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .map(new Function<ResponseBody, List<MedicineBean>>() {
                    @Override
                    public List<MedicineBean> apply(ResponseBody body) throws Exception {
                        try {
                            JSONObject jsonObject = new JSONObject(body.string());
                            if (jsonObject.get("status").equals("success")) {
                                Gson gson = new Gson();
                                List<MedicineBean> networkList = gson.fromJson(
                                        jsonObject.get("collection").toString(),
                                        new TypeToken<List<MedicineBean>>() {
                                        }.getType());

                                List<MedicineBean> localList = DataSupport.findAll(MedicineBean.class);
                                List<MedicineBean> resultList = new ArrayList<MedicineBean>();
                                for (MedicineBean medicineBean : networkList) {
                                    if (!localList.contains(medicineBean)) {
                                        resultList.add(medicineBean);
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
                .subscribe(new Observer<List<MedicineBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposables.add(d);
                    }

                    @Override
                    public void onNext(List<MedicineBean> value) {
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
    }

    @Override
    protected void loadMoreDataDone(List<MedicineBean> datas) {

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
        Observable<List<MedicineBean>> networkObservable = mService.getMedicineInfos()
                .subscribeOn(Schedulers.newThread())
                .map(new Function<ResponseBody, List<MedicineBean>>() {
                    @Override
                    public List<MedicineBean> apply(ResponseBody body) throws Exception {
                        try {
                            JSONObject jsonObject = new JSONObject(body.string());
                            if (jsonObject.get("status").equals("success")) {
                                Gson gson = new Gson();
                                List<MedicineBean> networkList = gson.fromJson(
                                        jsonObject.get("collection").toString(),
                                        new TypeToken<List<MedicineBean>>() {
                                        }.getType());
                                DataSupport.saveAll(networkList);
                                return networkList;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return Collections.emptyList();
                    }
                });


        Observable<List<MedicineBean>> localObservable = Observable.create(new ObservableOnSubscribe<List<MedicineBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<MedicineBean>> e) throws Exception {
                List<MedicineBean> localMedicineList = DataSupport.limit(20).find(MedicineBean.class);
                if (localMedicineList.size() == 0) {
                    e.onComplete();
                } else {
                    e.onNext(localMedicineList);
                }
            }
        });

        Observable.concat(localObservable, networkObservable)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<MedicineBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposables.add(d);
                    }

                    @Override
                    public void onNext(List<MedicineBean> medicineBeanList) {
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
