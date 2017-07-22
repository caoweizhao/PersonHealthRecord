package com.example.administrator.personhealthrecord.mvp.healthynews;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.activity.HealthyNewsDetailActivity;
import com.example.administrator.personhealthrecord.adapter.AbstractItemAdapter;
import com.example.administrator.personhealthrecord.bean.NewsBean;
import com.example.administrator.personhealthrecord.mvp.base.BaseFragment;
import com.example.administrator.personhealthrecord.mvp.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andy on 2017/7/19.
 */
public class HealthyNewsFragment extends BaseFragment implements IHealthyNewsFragment {
    private static HealthyNewsFragment mHealthyNewsFragment =null;
    public HealthyNewsFragment(){

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
    private AbstractItemAdapter adapter;
    private List<NewsBean> list;
    private Handler handler=new Handler();
    private RecyclerView.LayoutManager manager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new HealthyNewsPresenterImpl(this);
        presenter.getNewsList("");
        list=new ArrayList<>();
        list=TestDate.excute();
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
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                presenter.getNewsList("");
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(getActivity(), HealthyNewsDetailActivity.class);
                intent.putExtra("NewsBean",((NewsBean)adapter.getItem(position)));
                startActivity(intent);
            }
        });

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
        adapter.loadMoreFail();
    }

    @Override
    public void updateListItem(List<NewsBean> list) {
        final List<NewsBean> list1=list;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.addData(list1);
                adapter.loadMoreComplete();
            }
        },2000);

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
