package com.example.administrator.personhealthrecord.mvp.healthynews;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.adapter.AbstractItemAdapter;
import com.example.administrator.personhealthrecord.bean.NewsBean;
import com.example.administrator.personhealthrecord.mvp.base.BaseFragment;
import com.example.administrator.personhealthrecord.mvp.healthynewsdetali.HealthyNewsDetaliActivity;
import com.example.administrator.personhealthrecord.mvp.main.MainActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andy on 2017/7/19.
 */
public class HealthyNewsFragment extends BaseFragment implements IHealthyNewsFragment {
    private boolean isfirsttime=true;
    private static final String TAG="HealthyNewsFragment";
    private static HealthyNewsFragment mHealthyNewsFragment =null;
    private HealthyNewsFragment(){

    }
    public static HealthyNewsFragment getInstance() {
        synchronized(HealthyNewsFragment.class)
            {
                if(mHealthyNewsFragment==null)
                    {
                        mHealthyNewsFragment=new HealthyNewsFragment();
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
    private AbstractItemAdapter adapter;
    private List<NewsBean> list;
    private Handler handler=new Handler();
    private RecyclerView.LayoutManager manager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new HealthyNewsPresenterImpl(this);
//        list=new ArrayList<>();
//        list=TestDate.excute();
        manager=new LinearLayoutManager(getContext());
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.healthy_news_layout, container, false);
        ButterKnife.bind(this,view);
        return view;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter=new AbstractItemAdapter(R.layout.abstract_item,list,this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        presenter.getDBlist();
////下拉刷新
        layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d(TAG, "onUpFetch: ");
                // adapter.setUpFetching(true);
                if(!isfirsttime)
                    adapter.setEnableLoadMore(false);
                else
                {
                    presenter.getTodayNews();//假如是第一次的时候进行gettodaynews
                    Log.d(TAG, "onRefresh: "+"getnewsToday");
                }

                Log.d(TAG, "onUpFetch: "+adapter.isUpFetching());

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(isfirsttime)
                            isfirsttime=false;
                        layout.setRefreshing(false);
                    }
                },2000);
            }
        });

//        adapter.setUpFetchEnable(true);
//        adapter.setUpFetchListener(new BaseQuickAdapter.UpFetchListener() {
//            @Override public void onUpFetch() {
//                Log.d(TAG, "onUpFetch: ");
//                // adapter.setUpFetching(true);
//                if(!isfirsttime)
//                    adapter.setEnableLoadMore(false);
//                else
//                    presenter.getTodayNews();//假如是第一次的时候进行gettodaynews
//                Log.d(TAG, "onUpFetch: "+adapter.isUpFetching());
//
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        adapter.setEnableLoadMore(true);
//                        adapter.setUpFetchEnable(true);
//                        adapter.setUpFetching(false);
//                        if(isfirsttime)
//                            isfirsttime=false;
//                    }
//                },2000);
//            }
//        });

        //上拉加载更多
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
               List<NewsBean> list=adapter.getData();//现将list进行排序
                Collections.sort(list);
               NewsBean bean= list.get(list.size()-1);
                Log.d(TAG, "onLoadMoreRequested: "+adapter.getData().size()+"    "+adapter.getItemCount()+bean.getTime());
                presenter.getNewsBefore(bean.getdate());
            }
        });



        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(getActivity(), HealthyNewsDetaliActivity.class);
                intent.putExtra("NewsBean",((NewsBean)adapter.getItem(position)));
                startActivity(intent);
            }
        });
        adapter.setPreLoadNumber(0);
        initToolbar("健康");
        setUpWithActivity(view);
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
    public void updateAfterNews(List<NewsBean> list) {
        final List<NewsBean> list1=list;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.addData(list1);
                adapter.loadMoreComplete();
                adapter.setEnableLoadMore(true);
                adapter.setUpFetchEnable(true);
            }
        },1000);
    }

    //一开始更新最新的消息
    @Override
    public void updateTodayNews(List<NewsBean> list) {
        final List<NewsBean> list1=list;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.addData(0,list1);
                adapter.loadMoreComplete();
                adapter.setEnableLoadMore(true);
                adapter.setUpFetchEnable(true);
            }
        },1000);
    }

    @Override
    public void updatebeforeNews(final List<NewsBean> list) {
        final List<NewsBean> list1=list;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: "+"loadmoreBefor"+list.size());
                if(!(list1.size()==0))
                    adapter.addData(list1);
                else
                    adapter.setEnableLoadMore(false);
                adapter.loadMoreComplete();
            }
        },1000);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    private void setUpWithActivity(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((MainActivity) getActivity()).setUpWithToolbar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).openMenu();
            }
        });
    }


}
