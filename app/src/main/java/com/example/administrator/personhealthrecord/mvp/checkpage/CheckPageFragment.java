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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.adapter.AbstractItemAdapter;
import com.example.administrator.personhealthrecord.bean.CheckBean;
import com.example.administrator.personhealthrecord.bean.ImageBean;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.mvp.main.MainActivity;
import com.example.administrator.personhealthrecord.mvp.registandlogin.LoginActivity;
import com.example.administrator.personhealthrecord.mvp.reserve.ReserveActivity;
import com.example.administrator.personhealthrecord.mvp.reserveorder.ReserveOrderActivity;
import com.example.administrator.personhealthrecord.others.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckPageFragment extends ACheckPageFragment implements View.OnClickListener{

    private static final String TAG = "CheckPageFragment";
    private AbstractItemAdapter<CheckBean> adapter;
    private List<CheckBean> checkBeanList;
    private List<ImageBean> imageBeanList;
    @BindView(R.id.heath_check_banner01)
    Banner banner01;
    @BindView(R.id.heath_check_banner02)
    ImageView discoutnImage;
    @BindView(R.id.healthy_check_projectlsit)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.reserve_button)
    TextView reserve;
    @BindView(R.id.health_check_reserve_order_button)
    TextView reserveOrder;
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
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        checkBeanList = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_check_page, container, false);
        //设置图片加载器
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar("体检");
        setUpWithActivity(view);
        banner01.setImageLoader(new GlideImageLoader());
        //设置图片集合
        //banner设置方法全部调用完毕时最后调用
        banner01.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner01.start();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AbstractItemAdapter<CheckBean>(R.layout.abstract_item, checkBeanList, this.getContext());
        recyclerView.setAdapter(adapter);
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
    public void updateImages(List<ImageBean> imageBeens) {
        imageBeanList=imageBeens;
        List<String> urls=new ArrayList<>();
        for(ImageBean bean: imageBeens)
            urls.add(Contract.CheckPageAdvertismentImageUrl+bean.getImageUrl());
        banner01.update(urls);
    }

    @Override
    public void updateCheckItems(List<CheckBean> checkBeanList) {
        adapter.addData(checkBeanList);
    }

    @Override
    protected void initEvent() {
        discoutnImage.setOnClickListener(this);
        reserve.setOnClickListener(this);
        reserveOrder.setOnClickListener(this);
        banner01.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri url = Uri.parse(imageBeanList.get(position).getAdvertiseUrl());
                intent.setData(url);
                intent.addCategory(Intent. CATEGORY_DEFAULT);
                Intent newIntent = Intent.createChooser(intent,"选择浏览器");
                startActivity(newIntent);
            }
        });
    }

    @Override
    protected void initData() {
        imageBeanList=new ArrayList<>();
        Glide.with(this)
                .load(Contract.BASE_URL+"medical_package/getImageByFavourable")
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(discoutnImage);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d(TAG, "onCreateOptionsMenu: " + "Check");
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
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
                            case R.id.reserve_button:
                                Intent intent=new Intent(getActivity(), ReserveActivity.class);
                                intent.putExtra("IS_DISCOUNT",false);
                                startActivity(intent);
                                break;
                            case R.id.health_check_reserve_order_button:
                                    Intent intent2=new Intent(getActivity(), ReserveOrderActivity.class);
                                    startActivity(intent2);

                                break;
                            case R.id.heath_check_banner02:
                                Intent intent3=new Intent(getActivity(), ReserveActivity.class);
                                intent3.putExtra("IS_DISCOUNT",true);
                                startActivity(intent3);
                                break;
                            default:
                                break;
                        }
    }

//    public void gotToLogin()
//    {
//        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
//                .setTitleText("您还没有登录！")
//                .setContentText("是否去登录界面？")
//                .setCancelText("不了~")
//                .setConfirmText("去登陆->")
//                .showCancelButton(true)
//                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sDialog) {
//                        sDialog.cancel();
//                        Intent intent=new Intent(getActivity(), LoginActivity.class);
//                        startActivity(intent);
//                    }
//                })
//                .show();
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(banner01!=null)
            banner01.stopAutoPlay();
    }
}
