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
public class DiseaseFragment extends SocialPageBaseFragment<DiseaseService> {

    public static DiseaseFragment newInstance() {
        return new DiseaseFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.social_child_fragment;
    }

    @Override
    protected void fetchData() {
        mService.fetchDiseaseList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<List<DiseaseBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<DiseaseBean> value) {
                        for (DiseaseBean diseaseBean : value) {
                            Log.d("DiseaseFragment", "imageUrl:" + diseaseBean.getImageUrl());
                        }
                        fetchDataDone(value);
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
    protected void fetchDataDone(List datas) {
        mAdapter.getData().clear();
        mAdapter.addData(datas);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void loadMoreData() {
    }

    @Override
    protected void loadMoreDataDone(List datas) {
        int count = mAdapter.getItemCount();
        mAdapter.addData(datas);
        mAdapter.notifyItemRangeInserted(count, datas.size());
    }
}
