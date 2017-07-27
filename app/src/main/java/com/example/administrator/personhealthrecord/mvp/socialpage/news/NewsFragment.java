package com.example.administrator.personhealthrecord.mvp.socialpage.news;


import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.NewsBean;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfNewsBean;
import com.example.administrator.personhealthrecord.mvp.socialpage.SocialPageBaseFragment;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class NewsFragment extends SocialPageBaseFragment<NewsService> {

    Disposable mDisposable;

    public NewsFragment() {
        // Required empty public constructor
    }

    public static NewsFragment newInstance() {
        return new NewsFragment();
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
        mService.getNewsToday()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ResultUtilOfNewsBean, List<NewsBean>>() {
                    @Override
                    public List<NewsBean> apply(ResultUtilOfNewsBean value) throws Exception {
                        if (value.getStatus().equals("success")) {
                            return value.getCollection();
                        }
                        return null;
                    }
                }).subscribe(new Observer<List<NewsBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(List<NewsBean> value) {
                fetchDataDone(value);
            }

            @Override
            public void onError(Throwable e) {
                mSwipeRefreshLayout.setRefreshing(false);
                showMessage(getString(R.string.error_happen_msg));
            }

            @Override
            public void onComplete() {
                mSwipeRefreshLayout.setRefreshing(false);
                showMessage(getString(R.string.success_msg));
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
