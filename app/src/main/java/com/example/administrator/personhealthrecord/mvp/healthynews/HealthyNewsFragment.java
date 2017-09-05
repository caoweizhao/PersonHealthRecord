package com.example.administrator.personhealthrecord.mvp.healthynews;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.activity.HealthyNewsDetailActivity;
import com.example.administrator.personhealthrecord.adapter.HealthyNewsAdapter;
import com.example.administrator.personhealthrecord.base.BaseFragment;
import com.example.administrator.personhealthrecord.bean.NewsBean;
import com.example.administrator.personhealthrecord.mvp.main.MainActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * Created by andy on 2017/7/19.
 */
public class HealthyNewsFragment extends BaseFragment implements IHealthyNewsFragment {
    private int mUpPage = 0;//上面的刷新的page
    private long mUpTime;//上面的请求时间
    private int mDownPage = 0;//下面的时间
    private long mDownTime;//下面的时间
    private boolean mIsFirstTime = true;
    private static final String TAG = "HealthyNewsFragment";
    private static HealthyNewsFragment mHealthyNewsFragment = null;

    public HealthyNewsFragment() {

    }

    public static HealthyNewsFragment getInstance() {
        synchronized (HealthyNewsFragment.class) {
            if (mHealthyNewsFragment == null) {
                mHealthyNewsFragment = new HealthyNewsFragment();
            }
        }
        return mHealthyNewsFragment;
    }

    IHealthyNewsPresenter presenter;

    @BindView(R.id.health_news_recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.health_news_ProgressBar)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private HealthyNewsAdapter<NewsBean> mHealthyNewsAdapter;

    private List<NewsBean> mNewsBeanList = new ArrayList<>();
    private Handler handler = new Handler();
    private RecyclerView.LayoutManager manager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new HealthyNewsPresenterImpl(this);
        manager = new LinearLayoutManager(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.healthy_news_layout, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar("健康");
        setUpWithActivity(view);
        mHealthyNewsAdapter = new HealthyNewsAdapter<>(R.layout.abstract_item, mNewsBeanList, this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mHealthyNewsAdapter);
        presenter.getDBlist();
        //下拉刷新
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!mIsFirstTime) {
                    mHealthyNewsAdapter.setEnableLoadMore(false);
                    List<NewsBean> list = mHealthyNewsAdapter.getData();//现将list进行排序
                    Collections.sort(list);
                    if (list.size() > 0) {
                        NewsBean bean = list.get(0);
                        if (mUpPage == 1) {
                            mUpTime = bean.getTime();
                        }
                        presenter.getNewsAfter(mUpTime, mUpPage);
                    }
                } else {
                    presenter.getTodayNews();//假如是第一次的时候进行gettodaynews
                }

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mIsFirstTime)
                            mIsFirstTime = false;
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        //上拉加载更多
        mHealthyNewsAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                List<NewsBean> list = mHealthyNewsAdapter.getData();//现将list进行排序
                Collections.sort(list);
                NewsBean bean = list.get(list.size() - 1);
                Log.d(TAG, "onLoadMoreRequested: " + mHealthyNewsAdapter.getData().size() + "    " + mHealthyNewsAdapter.getItemCount() + bean.getTime());
                if (mDownPage == 0)
                    mDownTime = bean.getTime();
                presenter.getNewsBefore(mDownTime, mDownPage);
            }
        });
        //为RecycleVIew的Item设置点击listener
        mHealthyNewsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Intent intent = new Intent(getActivity(), HealthyNewsDetailActivity.class);
                Log.d(TAG, "onItemClick: " + ((NewsBean) adapter.getItem(position)).getTitle());
                intent.putExtra("NewsBean", ((NewsBean) adapter.getItem(position)));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    /*startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());*/
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity(),
                            Pair.create(view.findViewById(R.id.abstract_item_img), "image"),
                            Pair.create(view.findViewById(R.id.abstract_item_title), "title"))
                            .toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });
        mHealthyNewsAdapter.setPreLoadNumber(0);

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {
        mHealthyNewsAdapter.setUpFetchEnable(true);
        mHealthyNewsAdapter.loadMoreFail();
    }

    @Override
    public void updateAfterNews(final List<NewsBean> list) {
        final List<NewsBean> list1 = list;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (list.size() > 0)
                    mUpPage++;
                for (NewsBean bean : list1) {
                    if (!(mHealthyNewsAdapter.getData().contains(bean)))
                        mHealthyNewsAdapter.addData(bean);
                }
                Collections.sort(mHealthyNewsAdapter.getData());
                mHealthyNewsAdapter.notifyDataSetChanged();
                mHealthyNewsAdapter.loadMoreComplete();
            }
        }, 500);
    }

    //一开始更新最新的消息
    @Override
    public void updateTodayNews(List<NewsBean> list) {
        final List<NewsBean> list1 = list;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (list1.size() > 0)
                    mUpPage++;
                for (NewsBean bean : list1) {
                    if (!(mHealthyNewsAdapter.getData().contains(bean)))
                        mHealthyNewsAdapter.addData(0, bean);
                }
                Collections.sort(mHealthyNewsAdapter.getData());
                mHealthyNewsAdapter.notifyDataSetChanged();
                mHealthyNewsAdapter.loadMoreComplete();
                mHealthyNewsAdapter.setEnableLoadMore(true);
                mHealthyNewsAdapter.setUpFetchEnable(true);
            }
        }, 500);
    }

    @Override
    public void updateBeforeNews(final List<NewsBean> list) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (list.size() > 0)
                    mDownPage++;
                if (!(list.size() == 0)) {
                    for (NewsBean bean : list) {
                        if (!(mHealthyNewsAdapter.getData().contains(bean)))
                            mHealthyNewsAdapter.addData(bean);
                    }
                    Collections.sort(mHealthyNewsAdapter.getData());
                } else
                    mHealthyNewsAdapter.setEnableLoadMore(false);
                mHealthyNewsAdapter.notifyDataSetChanged();
                mHealthyNewsAdapter.loadMoreComplete();
            }
        }, 500);
    }

    /**
     * 更新获得数据库的消息
     */
    @Override
    public void updateAllBDsNews(List<NewsBean> list) {
        final List<NewsBean> list1 = list;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (NewsBean bean : list1) {
                    if (!(mHealthyNewsAdapter.getData().contains(bean)))
                        mHealthyNewsAdapter.addData(0, bean);
                }
                Collections.sort(mHealthyNewsAdapter.getData());
                mHealthyNewsAdapter.notifyDataSetChanged();
                mHealthyNewsAdapter.loadMoreComplete();
                mHealthyNewsAdapter.setEnableLoadMore(true);
                mHealthyNewsAdapter.setUpFetchEnable(true);
            }
        }, 500);
    }

    private void setUpWithActivity(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setBackgroundColor(Color.parseColor(getString(R.string.health_color)));
        ((MainActivity) getActivity()).setUpWithToolbar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).openMenu();
            }
        });
    }
}
