package com.example.administrator.personhealthrecord.mvp.homepage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.ExpertBean;
import com.example.administrator.personhealthrecord.others.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePageFragment extends AHomePageFragment {

    private Banner mImageBanner;
    private Banner mExpertsBanner;

    public HomePageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomePageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePageFragment newInstance() {
        return new HomePageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        mImageBanner = (Banner) view.findViewById(R.id.banner);
        //设置图片加载器
        mImageBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        //banner设置方法全部调用完毕时最后调用
        mImageBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mImageBanner.start();
        return view;
    }

    @Override
    public AHomePagePresenter createPresenter() {
        return new HomePagePresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void updateImages(List<String> urls) {
        mImageBanner.update(urls);
    }

    @Override
    public void updateExperts(List<ExpertBean> expertBeenF) {

    }
}
