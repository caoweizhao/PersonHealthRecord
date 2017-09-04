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

import static org.litepal.crud.DataSupport.findAll;

public class NewsFragment extends SocialPageBaseFragment<NewsBean, NewsService> {

    private boolean mIsTodayNewLast;
    private boolean mIsPreNewsLast;
    private boolean mIsPostNewsLast;
    private int mPrePage = 0;
    private int mPostPage = 0;
    private int mTodayPage = 0;
    private int mCountPerPage = 10;

    private long mFirstTimeStamp = -1;
    private long mLastTimeStamp = -1;


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

    /**
     * 初始化数据,先从网络中获取0页10条数据，如果不足十条，则从数据库中加载，直到数量达到10条
     */
    @Override
    protected void initData() {
        mSwipeRefreshLayout.setRefreshing(true);
        mAdapter.setEnableLoadMore(false);

        Observable<List<NewsBean>> localObservable = Observable.create(new ObservableOnSubscribe<List<NewsBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<NewsBean>> e) throws Exception {
                List<NewsBean> localNewsBeans = DataSupport.offset(mAdapter.getData().size())
                        .order("time desc")
                        .limit(mCountPerPage)
                        .find(NewsBean.class);
                e.onNext(localNewsBeans);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread());

        Observable<List<NewsBean>> netWorkObservable = mService.getNewsToday(mTodayPage, mCountPerPage)
                .subscribeOn(Schedulers.newThread())
                .map(new Function<ResultUtilOfNewsBean, List<NewsBean>>() {
                    @Override
                    public List<NewsBean> apply(ResultUtilOfNewsBean bean) throws Exception {
                        if (bean.getStatus().equals("success")) {
                            List<NewsBean> networkList = bean.getObject().getContent();
                            if (bean.getObject().isLast()) {
                                mIsTodayNewLast = true;
                            } else {
                                mTodayPage++;
                            }
                            //保存数据库
                            List<NewsBean> localList = findAll(NewsBean.class);
                            List<NewsBean> resultList = new ArrayList<>();
                            for (NewsBean newsBean : networkList) {
                                if (!localList.contains(newsBean)) {
                                    resultList.add(newsBean);
                                }
                            }
                            DataSupport.saveAll(resultList);
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
                        mAdapter.setEnableLoadMore(true);
                    }
                });
    }

    /**
     * 初始化数据完成，记录第一条跟最后一条的timestamp，用来进行分页
     *
     * @param datas
     */
    @Override
    protected void initDataDone(List<NewsBean> datas) {
        Collections.sort(datas);
        Collections.reverse(datas);
        for (NewsBean data : datas) {
            if (!mDataList.contains(data)) {
                mAdapter.addData(0, data);
                mAdapter.notifyItemInserted(0);
                if (mDataList.size() > 10) {
                    mDataList.remove(mDataList.size() - 1);
                    mAdapter.notifyItemRemoved(mDataList.size() - 1);
                }
            }
        }
        if (mDataList.size() > 0) {
            mFirstTimeStamp = mDataList.get(0).getTime();
            Log.d("NewsFragment", "initDataDone" + mDataList.get(0).getTime());
            mLastTimeStamp = mDataList.get(mDataList.size() - 1).getTime();
        }
        List<NewsBean> list = DataSupport.findAll(NewsBean.class);
        if (list.size() > 0) {
            mLastTimeStamp = list.get(list.size() - 1).getTime();
        }
        mRecyclerView.scrollToPosition(0);
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
            //如果上次刷新为最后一条，则下拉刷新仍请求当前页，否则请求下一页
            Log.d("NewsFragment","refreshData"+mFirstTimeStamp);
            mService.getLatestNews(mFirstTimeStamp, mPrePage, mCountPerPage)
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
                                List<NewsBean> networkList = value.getObject().getContent();
                                for (int i = 0; i < networkList.size(); i++) {
                                    Log.d("NewsFragment", networkList.get(i).getTitle());
                                }
                                Log.d("NewsFragment", "onNext" + networkList.size());
                                List<NewsBean> localList = findAll(NewsBean.class);
                                List<NewsBean> resultList = new ArrayList<>();

                                if (!value.getObject().isLast()) {
                                    mPrePage++;
                                }

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
     * 当今天的数据加载完成后，获取更多数据(数据库有则从数据库读，没有则从网络读)
     * 否则继续进行加载今天的数据（分页加载，每次十条数据）
     */
    @Override
    protected void loadMoreData() {

        //如果今天的数据未请求完，继续获取今天的数据
        if (!mIsTodayNewLast && mTodayPage != 0) {
            mService.getNewsToday(mTodayPage, mCountPerPage)
                    .subscribeOn(Schedulers.newThread())
                    .map(new Function<ResultUtilOfNewsBean, List<NewsBean>>() {
                        @Override
                        public List<NewsBean> apply(ResultUtilOfNewsBean bean) throws Exception {
                            if (bean.getStatus().equals("success")) {
                                List<NewsBean> networkList = bean.getObject().getContent();
                                if (bean.getObject().isLast()) {
                                    mIsTodayNewLast = true;
                                } else {
                                    mTodayPage++;
                                }
                                //保存数据库
                                List<NewsBean> localList = findAll(NewsBean.class);
                                List<NewsBean> resultList = new ArrayList<>();
                                for (NewsBean newsBean : networkList) {
                                    if (!localList.contains(newsBean)) {
                                        resultList.add(newsBean);
                                    }
                                }
                                DataSupport.saveAll(resultList);
                                return resultList;
                            }
                            return Collections.emptyList();
                        }
                    }).subscribe(new Observer<List<NewsBean>>() {
                @Override
                public void onSubscribe(Disposable d) {
                    mDisposables.add(d);
                }

                @Override
                public void onNext(List<NewsBean> value) {
                    refreshDataDone(value);
                }

                @Override
                public void onError(Throwable e) {
                    mSwipeRefreshLayout.setRefreshing(false);
                    showMessage(e.getMessage());
                    mAdapter.setEnableLoadMore(true);
                }

                @Override
                public void onComplete() {
                }
            });
            return;
        }

        //读取数据库
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
                            mLastTimeStamp = mDataList.get(mDataList.size() - 1).getTime();
                            e.onComplete();
                        }
                    }
                }).subscribeOn(Schedulers.newThread());

        //读取网络数据
        Observable<List<NewsBean>> networkObservable = mService.getOlderNews(
                mLastTimeStamp, mPostPage, mCountPerPage)
                .subscribeOn(Schedulers.newThread())
                .map(new Function<ResultUtilOfNewsBean, List<NewsBean>>() {
                    @Override
                    public List<NewsBean> apply(ResultUtilOfNewsBean bean) throws Exception {
                        if (bean.getStatus().equals("success")) {
                            if (mIsPostNewsLast) {
                                return Collections.emptyList();
                            }
                            if (!bean.getObject().isLast()) {
                                mPostPage++;
                            } else {
                                mIsPostNewsLast = true;
                            }
                            List<NewsBean> localList = DataSupport.findAll(NewsBean.class);
                            List<NewsBean> list = bean.getObject().getContent();
                            Collections.sort(list);
                            List<NewsBean> resultList = new ArrayList<NewsBean>();
                            for (NewsBean newsBean : list) {
                                if (!localList.contains(newsBean)) {
                                    resultList.add(newsBean);
                                }
                            }
                            DataSupport.saveAll(resultList);
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
                        showMessage("获取失败！");
                    }

                    @Override
                    public void onComplete() {
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
