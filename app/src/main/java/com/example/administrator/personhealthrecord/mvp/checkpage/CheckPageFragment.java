package com.example.administrator.personhealthrecord.mvp.checkpage;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.activity.CaseListActivity;
import com.example.administrator.personhealthrecord.adapter.AbstractItemAdapter;
import com.example.administrator.personhealthrecord.bean.CheckBean;
import com.example.administrator.personhealthrecord.bean.ImageBean;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.mvp.main.MainActivity;
import com.example.administrator.personhealthrecord.mvp.reserve.ReserveActivity;
import com.example.administrator.personhealthrecord.mvp.reserve_order.ReserveOrderActivity;
import com.example.administrator.personhealthrecord.others.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckPageFragment extends ACheckPageFragment implements View.OnClickListener{

    private static final String TAG = "CheckPageFragment";
    private AbstractItemAdapter<CheckBean> mAdapter;
    private List<CheckBean> mCheckBeanList;
    private List<ImageBean> mImageBeanList;
    @BindView(R.id.heath_check_top_ad_banner)
    Banner mTopADBanner;
    @BindView(R.id.heath_check_discount_img)
    ImageView mDiscountImage;
    @BindView(R.id.healthy_check_projectlsit)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.reserve_view)
    TextView mReserveView;
    @BindView(R.id.health_check_reserve_order_view)
    TextView mReserveOrderView;
    @BindView(R.id.healthy_check_myreport)
    TextView myReport;
    public CheckPageFragment() {
        // Required empty public constructor
    }
    public static CheckPageFragment newInstance() {
        return new CheckPageFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mCheckBeanList = new ArrayList<>();
        //设置图片加载器
        return inflater.inflate(R.layout.fragment_check_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar("体检");
        setUpWithActivity(view);
        mTopADBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        //banner设置方法全部调用完毕时最后调用
        mTopADBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mTopADBanner.start();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new AbstractItemAdapter<>(R.layout.abstract_item, mCheckBeanList, this.getContext());
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.onRequestData();
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
    public void updateImages(List<ImageBean> imageBeanList) {
        this.mImageBeanList =imageBeanList;
        List<String> urls=new ArrayList<>();
        for(ImageBean bean: imageBeanList)
            urls.add(Contract.CheckPageAdvertisementImageUrl +bean.getImageUrl());
        mTopADBanner.update(urls);
    }

    @Override
    public void updateCheckItems(List<CheckBean> checkBeanList) {
        mAdapter.addData(checkBeanList);
    }

    @Override
    protected void initEvent() {
        myReport.setOnClickListener(this);
        mDiscountImage.setOnClickListener(this);
        mReserveView.setOnClickListener(this);
        mReserveOrderView.setOnClickListener(this);
        mTopADBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri url = Uri.parse(mImageBeanList.get(position).getAdvertiseUrl());
                intent.setData(url);
                intent.addCategory(Intent. CATEGORY_DEFAULT);
                Intent newIntent = Intent.createChooser(intent,"选择浏览器");
                startActivity(newIntent);
            }
        });
    }

    @Override
    protected void initData() {
        mImageBeanList =new ArrayList<>();
        Glide.with(this)
                .load(Contract.BASE_URL+"medical_package/getImageByFavourable")
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mDiscountImage);
    }

    private void setUpWithActivity(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setBackgroundColor(Color.parseColor(getString(R.string.check_page_color)));
        ((MainActivity) getActivity()).setUpWithToolbar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).openMenu();
            }
        });
    }

    @Override
    public void onClick(View v) {
                switch (v.getId())
                        {
                            case R.id.reserve_view:
                                Intent intent=new Intent(getActivity(), ReserveActivity.class);
                                intent.putExtra("IS_DISCOUNT",false);
                                startActivity(intent);
                                break;
                            case R.id.health_check_reserve_order_view:
                                    Intent intent2=new Intent(getActivity(), ReserveOrderActivity.class);
                                    startActivity(intent2);
                                break;
                            case R.id.heath_check_discount_img:
                                Intent intent3=new Intent(getActivity(), ReserveActivity.class);
                                intent3.putExtra("IS_DISCOUNT",true);
                                startActivity(intent3);
                                break;
                            case R.id.healthy_check_myreport:
                                Intent intent4=new Intent(getActivity(), CaseListActivity.class);
                                startActivity(intent4);
                            default:
                                break;
                        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mTopADBanner !=null)
            mTopADBanner.stopAutoPlay();
    }
}
