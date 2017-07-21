package com.example.administrator.personhealthrecord.mvp.checkpage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.adapter.AbstractItemAdapter;
import com.example.administrator.personhealthrecord.bean.CheckBean;
import com.example.administrator.personhealthrecord.others.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckPageFragment extends ACheckPageFragment {

    private AbstractItemAdapter<CheckBean> adapter;
    private List<CheckBean> checkBeanList;

    @BindView(R.id.heath_check_banner01)
    Banner banner01;
    @BindView(R.id.heath_check_banner02)
    Banner banner02;
    @BindView(R.id.healthy_check_projectlsit)
    RecyclerView recyclerView;
            public CheckPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SocialPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckPageFragment newInstance() {
        return new CheckPageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        checkBeanList=new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_check_page, container, false);
        ButterKnife.bind(this,view);
        //设置图片加载器
        banner01.setImageLoader(new GlideImageLoader());
        //设置图片集合
        //banner设置方法全部调用完毕时最后调用
        banner01.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner01.start();
        banner02.setImageLoader(new GlideImageLoader());
        //设置图片集合
        //banner设置方法全部调用完毕时最后调用
        banner02.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner02.start();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new AbstractItemAdapter<CheckBean>(R.layout.abstract_item,checkBeanList,this.getContext());
        recyclerView.setAdapter(adapter);
        mPresenter.onRequestData();
        return view;
    }

    @Override
    public ACheckPagePresenter createPresenter() {
        return new CheckPagePresenter();
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void updateImages(List<String> urls) {
        banner01.update(urls);
        banner02.update(urls);
    }

    @Override
    public void updateCheckItems(List<CheckBean> checkBeanList) {
        adapter.addData(checkBeanList);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
