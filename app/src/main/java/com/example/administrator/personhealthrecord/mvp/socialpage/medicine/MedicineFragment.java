package com.example.administrator.personhealthrecord.mvp.socialpage.medicine;


import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.MedicineBean;
import com.example.administrator.personhealthrecord.mvp.socialpage.SocialPageBaseFragment;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MedicineFragment extends SocialPageBaseFragment<MedicineService> {

    Disposable mDisposable;

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
    protected void fetchData() {
        mSwipeRefreshLayout.setRefreshing(true);
        mService.getMedicineInfos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<List<MedicineBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(List<MedicineBean> value) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 获取数据完毕
     *
     * @param datas
     */
    @Override
    protected void fetchDataDone(List datas) {
        mAdapter.setEnableLoadMore(true);
        mAdapter.getData().clear();
        mAdapter.addData(datas);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 获取更多数据
     */
    @Override
    protected void loadMoreData() {
    }

    @Override
    protected void loadMoreDataDone(List datas) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }
}
