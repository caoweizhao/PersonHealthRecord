package com.example.administrator.personhealthrecord.mvp.checkpage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.CheckBean;
import com.example.administrator.personhealthrecord.others.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckPageFragment extends ACheckPageFragment {

    private Banner mImageBanner;

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
        View view = inflater.inflate(R.layout.fragment_check_page, container, false);
        mImageBanner = (Banner) view.findViewById(R.id.banner);
        //设置图片加载器
        mImageBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        //banner设置方法全部调用完毕时最后调用
        mImageBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mImageBanner.start();
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
        mImageBanner.update(urls);
    }

    @Override
    public void updateCheckItems(List<CheckBean> checkBeanList) {

    }
}
