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

import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * Created by andy on 2017/7/19.
 */
public class HealthyNewsFragment extends BaseFragment implements IHealthyNewsFragment {
    private int upPage=0;//上面的刷新的page
    private long upTime;//上面的请求时间
    private int downPage=0;//下面的时间
    private long downTime;//下面的时间
    private boolean isfirsttime = true;
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
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.health_news_ProgressBar)
    SwipeRefreshLayout layout;

    private HealthyNewsAdapter<NewsBean> adapter;

    private List<NewsBean> list;
    private Handler handler = new Handler();
    private RecyclerView.LayoutManager manager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new HealthyNewsPresenterImpl(this);
//        list=new ArrayList<>();
//        list=TestDate.excute();
        manager = new LinearLayoutManager(getContext());
//        presenter.test();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.healthy_news_layout, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar("健康");
        setUpWithActivity(view);
        adapter = new HealthyNewsAdapter<NewsBean>(R.layout.abstract_item, list, this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        presenter.getDBlist();
        ////下拉刷新
        layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d(TAG, "onUpFetch: ");
                // adapter.setUpFetching(true);
                if (!isfirsttime) {
                    adapter.setEnableLoadMore(false);
                    List<NewsBean> list = adapter.getData();//现将list进行排序
                    Collections.sort(list);
                    if (list.size() > 0) {
                        NewsBean bean = list.get(0);
                        Log.d(TAG, "onRefresh: "+bean.getTitle()+bean.getTime());
                        if(upPage==1)
                        {
                            upTime=bean.getTime();
                        }
                        presenter.getNewsAfter(upTime,upPage);
                    }
                } else {
                    presenter.getTodayNews();//假如是第一次的时候进行gettodaynews
                    Log.d(TAG, "onRefresh: " + "getnewsToday");
                }
                Log.d(TAG, "onUpFetch: " + adapter.isUpFetching());

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (isfirsttime)
                            isfirsttime = false;
                        layout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        //上拉加载更多
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                List<NewsBean> list = adapter.getData();//现将list进行排序
                Collections.sort(list);
                NewsBean bean = list.get(list.size() - 1);
                Log.d(TAG, "onLoadMoreRequested: " + adapter.getData().size() + "    " + adapter.getItemCount() + bean.getTime());
                if(downPage==0)
                    downTime=bean.getTime();
                presenter.getNewsBefore(downTime,downPage);
            }
        });

/**
 * 为RcycleVIew的Item设置点击listner
 */
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Intent intent = new Intent(getActivity(), HealthyNewsDetailActivity.class);
                Log.d(TAG, "onItemClick: " + ((NewsBean) adapter.getItem(position)).getTitle());
                intent.putExtra("NewsBean", ((NewsBean) adapter.getItem(position)));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    /*startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());*/
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity(),
                            Pair.create(view.findViewById(R.id.abstract_item__img), "image"),
                            Pair.create(view.findViewById(R.id.abstract_item__title), "title"))
                            .toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });
        adapter.setPreLoadNumber(0);

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
    public void hidProgressDialog() {
        adapter.setUpFetchEnable(true);
        adapter.loadMoreFail();
    }

    @Override
    public void updateAfterNews(final List<NewsBean> list) {
        final List<NewsBean> list1 = list;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(list.size()>0)
                    upPage++;
                for (NewsBean bean : list1) {
                    if (!(adapter.getData().contains(bean)))
                        adapter.addData(bean);
                }
                Collections.sort(adapter.getData());
                adapter.notifyDataSetChanged();
                adapter.loadMoreComplete();
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
                if(list1.size()>0)
                    upPage++;
                for (NewsBean bean : list1) {
                    if (!(adapter.getData().contains(bean)))
                        adapter.addData(0, bean);
                }
                Collections.sort(adapter.getData());
                adapter.notifyDataSetChanged();
                adapter.loadMoreComplete();
                adapter.setEnableLoadMore(true);
                adapter.setUpFetchEnable(true);
            }
        }, 500);
    }

    @Override
    public void updatebeforeNews(final List<NewsBean> list) {
        final List<NewsBean> list1 = list;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(list.size()>0)
                    downPage++;
                Log.d(TAG, "run: " + "loadmoreBefor" + list1.size());
                if (!(list1.size() == 0)) {
                    for (NewsBean bean : list1) {
                        if (!(adapter.getData().contains(bean)))
                            adapter.addData(bean);
                    }
                    Collections.sort(adapter.getData());

                } else
                    adapter.setEnableLoadMore(false);
                adapter.notifyDataSetChanged();
                adapter.loadMoreComplete();
            }
        }, 500);
    }

    /**
     * 更新获得数据库的消息
     * @param list
     */
    @Override
    public void updateallBDsNews(List<NewsBean> list) {
        final List<NewsBean> list1 = list;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (NewsBean bean : list1) {
                    if (!(adapter.getData().contains(bean)))
                        adapter.addData(0, bean);
                }
                Collections.sort(adapter.getData());
                adapter.notifyDataSetChanged();
                adapter.loadMoreComplete();
                adapter.setEnableLoadMore(true);
                adapter.setUpFetchEnable(true);
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
