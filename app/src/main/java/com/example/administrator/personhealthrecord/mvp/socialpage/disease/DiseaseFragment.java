package com.example.administrator.personhealthrecord.mvp.socialpage.disease;


import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.DiseaseBean;
import com.example.administrator.personhealthrecord.mvp.socialpage.SocialPageBaseFragment;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiseaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiseaseFragment extends SocialPageBaseFragment<DiseaseBean, DiseaseService> {

    private Disposable mDisposable;

    public static DiseaseFragment newInstance() {
        return new DiseaseFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.social_child_fragment;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void refreshData() {
        mService.fetchDiseaseList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<List<DiseaseBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(List<DiseaseBean> value) {
                        for (DiseaseBean diseaseBean : value) {
                            Log.d("DiseaseFragment", "imageUrl:" + diseaseBean.getImageUrl());
                        }
                        initDataDone(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("DiseaseFragment", "onError:" + e.getMessage());
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onComplete() {
                        Log.d("DiseaseFragment", "complete");
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    @Override
    protected void loadMoreData() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }


}
