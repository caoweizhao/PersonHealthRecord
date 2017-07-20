package com.example.administrator.personhealthrecord.mvp.healthynews;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.adapter.AbstractItemAdapter;
import com.example.administrator.personhealthrecord.application.MyApplication;
import com.example.administrator.personhealthrecord.bean.NewsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andy on 2017/7/19.
 */
public class HealthyNewsFragement extends Fragment implements IHealthyNewsFragment {

    IHealthyNewsPresenter presenter;

    @BindView(R.id.health_news_recycleview)
    RecyclerView recyclerView;

    private AbstractItemAdapter adapter;
    private List<NewsBean> list;
    private Handler handler;
    private RecyclerView.LayoutManager manager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new HealthyNewsPresenterImpl(this);
        presenter.getNewsList("");
        list=new ArrayList<>();
        list=TestDate.excute();
        manager=new LinearLayoutManager(getContext());
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
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hidProgressDialog() {

    }

    @Override
    public void updateListItem(List<NewsBean> list) {
        final List<NewsBean> list1=list;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.addData(list1);
            }
        },1000);

    }
}
