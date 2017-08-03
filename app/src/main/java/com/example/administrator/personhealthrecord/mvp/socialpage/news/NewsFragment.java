package com.example.administrator.personhealthrecord.mvp.socialpage.news;


import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.activity.NewsDetailActivity;
import com.example.administrator.personhealthrecord.bean.NewsBean;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfNewsBean;
import com.example.administrator.personhealthrecord.mvp.socialpage.SocialPageBaseFragment;

import org.litepal.crud.DataSupport;

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

import static io.reactivex.Observable.create;
import static org.litepal.crud.DataSupport.findAll;

public class NewsFragment extends SocialPageBaseFragment<NewsBean, NewsService> {

    List<Disposable> mDisposables = new ArrayList<>();

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

    @Override
    protected void initData() {
        mSwipeRefreshLayout.setRefreshing(true);
        mAdapter.setEnableLoadMore(false);

        Observable<List<NewsBean>> localObservable = create(new ObservableOnSubscribe<List<NewsBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<NewsBean>> e) throws Exception {
                List<NewsBean> localNewsBeans = DataSupport.offset(mAdapter.getData().size())
                        .order("time desc")
                        .limit(10)
                        .find(NewsBean.class);
                Log.d("NewsFragment", "subscribe" + localNewsBeans.size());
                e.onNext(localNewsBeans);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread());

        Observable<List<NewsBean>> netWorkObservable = mService.getNewsToday()
                .subscribeOn(Schedulers.newThread())
                .map(new Function<ResultUtilOfNewsBean, List<NewsBean>>() {
                    @Override
                    public List<NewsBean> apply(ResultUtilOfNewsBean bean) throws Exception {
                        if (bean.getStatus().equals("success")) {
                            bean.getCollection();
                            /**
                             * 保存数据库
                             */
                            List<NewsBean> localList = findAll(NewsBean.class);
                            List<NewsBean> networkList = bean.getCollection();
                            List<NewsBean> resultList = new ArrayList<NewsBean>();
                            for (NewsBean newsBean : networkList) {
                                if (!localList.contains(newsBean)) {
                                    resultList.add(newsBean);
                                    Log.d("NewsFragment", "save");
                                }
                            }
                            DataSupport.saveAll(resultList);
                            Log.d("NewsFragment", "dbSize:" + DataSupport.findAll(NewsBean.class).size());
                            return resultList;
                        }
                        return Collections.emptyList();
                    }
                });

        Observable.concat(localObservable, netWorkObservable)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<NewsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposables.add(d);
                    }

                    @Override
                    public void onNext(List<NewsBean> value) {
                        initDataDone(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        showMessage(e.getMessage());
                        mAdapter.setEnableLoadMore(true);
                    }

                    @Override
                    public void onComplete() {
                        mSwipeRefreshLayout.setRefreshing(false);
                        showMessage(getString(R.string.success_msg));
                        mAdapter.setEnableLoadMore(true);
                    }
                });
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(), NewsDetailActivity.class);
                intent.putExtra("NewsBean", mAdapter.getItem(position));
                startActivity(intent);
            }
        });

    }

    /**
     * 下拉获取最新数据
     */
    @Override
    protected void refreshData() {
        if (mDataList.size() > 0) {
            mService.getLatestNews(mDataList.get(0).getTime())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(new Observer<ResultUtilOfNewsBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            mDisposables.add(d);
                        }

                        @Override
                        public void onNext(ResultUtilOfNewsBean value) {
                            if (value.getStatus().equals("success")) {
                                List<NewsBean> networkList = value.getCollection();
                                List<NewsBean> localList = DataSupport.findAll(NewsBean.class);
                                List<NewsBean> resultList = new ArrayList<>();

                                for (NewsBean newsBean : networkList) {
                                    if (!localList.contains(newsBean)) {
                                        resultList.add(newsBean);
                                    }
                                }
                                DataSupport.saveAll(resultList);
                                refreshDataDone(resultList);
                            } else {
                                mSwipeRefreshLayout.setRefreshing(false);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            mSwipeRefreshLayout.setRefreshing(false);
                            showMessage(e.getMessage());
                            mAdapter.setEnableLoadMore(true);
                        }

                        @Override
                        public void onComplete() {
                            mSwipeRefreshLayout.setRefreshing(false);
                            mAdapter.setEnableLoadMore(true);
                        }
                    });
        } else {
            initData();
        }

    }

    /**
     * 获取更多数据(数据库有则从数据库读，没有则从网络读)
     */
    @Override
    protected void loadMoreData() {

        /**
         * 读取数据库
         */
        Observable<List<NewsBean>> localObservable = Observable.create(
                new ObservableOnSubscribe<List<NewsBean>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<NewsBean>> e) throws Exception {
                        List<NewsBean> result = DataSupport.order("time desc")
                                .offset(mAdapter.getData().size())
                                .limit(10)
                                .find(NewsBean.class);
                        //数据库有数据，则从数据库读取
                        if (result.size() > 0) {
                            e.onNext(result);
                        } else {
                            e.onComplete();
                        }
                    }
                }).subscribeOn(Schedulers.newThread());

        /**
         * 读取网络数据
         */
        Observable<List<NewsBean>> networkObservable = mService.getOlderNews(
                mDataList.get(mDataList.size() - 1).getTime())
                .subscribeOn(Schedulers.newThread())
                .map(new Function<ResultUtilOfNewsBean, List<NewsBean>>() {
                    @Override
                    public List<NewsBean> apply(ResultUtilOfNewsBean bean) throws Exception {
                        if (bean.getStatus().equals("success")) {
                            List<NewsBean> list = bean.getCollection();
                            Log.d("NewsFragment","list:"+list);
                            Collections.sort(list);
                            DataSupport.saveAll(list);
                            return list;
                        } else {
                            return Collections.emptyList();
                        }
                    }
                });

        Observable.concat(localObservable, networkObservable)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<NewsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposables.add(d);
                    }

                    @Override
                    public void onNext(List<NewsBean> value) {
                        if (value.size() == 0) {
                            mAdapter.loadMoreEnd(true);
                            showMessage("没有更多数据啦！");
                        } else {
                            loadMoreDataDone(value);
                            mAdapter.loadMoreComplete();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mAdapter.loadMoreFail();
                        showMessage(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("NewsFragment", "onComplete");
                        mAdapter.loadMoreComplete();
                    }
                });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (Disposable disposable : mDisposables
                ) {
            if (disposable != null) {
                disposable.dispose();
            }
        }
    }
}
