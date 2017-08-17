package com.example.administrator.personhealthrecord.mvp.homepage;


import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.activity.DoctorDetailActivity;
import com.example.administrator.personhealthrecord.activity.HospitalDetailActivity;
import com.example.administrator.personhealthrecord.activity.HospitalListActivity;
import com.example.administrator.personhealthrecord.activity.MapAcitvity;
import com.example.administrator.personhealthrecord.activity.SearchResultActivity;
import com.example.administrator.personhealthrecord.adapter.HospitalAdapter;
import com.example.administrator.personhealthrecord.bean.AdvertisementBean;
import com.example.administrator.personhealthrecord.bean.ExpertBean;
import com.example.administrator.personhealthrecord.bean.HospitalBean;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.mvp.chat.ChatActivity;
import com.example.administrator.personhealthrecord.mvp.main.MainActivity;
import com.example.administrator.personhealthrecord.others.GlideImageLoader;
import com.example.administrator.personhealthrecord.util.AnimateUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePageFragment extends AHomePageFragment {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.self_registered)
    LinearLayout mSelfRegistered;
    @BindView(R.id.private_doctor)
    TextView mPrivateDoctor;
    @BindView(R.id.medical_consultation)
    TextView mMedicalConsultation;
    @BindView(R.id.expert_type_spinner)
    Spinner mExpertTypeSpinner;
    @BindView(R.id.home_page_expert_banner)
    Banner mExpertsBanner;
    @BindView(R.id.home_page_recyclerView)
    RecyclerView mHomePageRecyclerView;
    @BindView(R.id.home_page_img_banner)
    Banner mImageBanner;
    @BindView(R.id.search_view)
    SearchView mSearchView;

    private boolean isExpand = false;
    private HospitalAdapter mHospitalAdapter;

    private List<String> mExpertsImageUrl = new ArrayList<>();
    private List<String> mAdvertisementImageUrl = new ArrayList<>();
    private List<ExpertBean> mExpertsBean = new ArrayList<>();
    private List<AdvertisementBean> mAdvertisementBeenList = new ArrayList<>();

    @BindView(R.id.near_by_hospital)
    CardView mCardView;
    @BindView(R.id.map_text_view)
    TextView mapTextView;

    public HomePageFragment() {
        // Required empty public constructor
    }

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
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBanner();
        initToolbar("首页");
        setUpWithActivity(view);
    }

    private void setUpWithActivity(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((MainActivity) getActivity()).setUpWithToolbar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
                mSearchView.clearFocus();
                ((MainActivity) getActivity()).openMenu();
            }
        });
    }

    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menu.clear();
        Log.d("HomePageFragment", "onCreateOptionsMenu");
        menuInflater.inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_item);
        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                isExpand = true;
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                isExpand = false;
                return true;
            }
        });
        mSearchView = (SearchView) MenuItemCompat.getActionView(menuItem);

    }*/

    @Override
    protected void initEvent() {
        mSearchView.setIconified(true);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint(getResources().getString(R.string.search_hint));
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO: 2017-7-21 搜索事件处理
                Intent intent = new Intent(getContext(), SearchResultActivity.class);
                intent.putExtra("data", query.replaceAll(" ", ""));
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        /**
         * 地图
         */
        mapTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapAcitvity.class);
                startActivity(intent);
            }
        });
        mExpertTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO: 2017-7-20  获取推荐专家
                mPresenter.onSpinnerItemSelected(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /**
         * 私人医生
         */
        mPrivateDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimateUtil.createCircularReveal(v);
            }
        });

        /**
         * 医院
         */
        mHospitalAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                AnimateUtil.createCircularReveal(view);
                Intent intent = new Intent(getContext(), HospitalDetailActivity.class);
                intent.putExtra("data", mHospitalAdapter.getItem(position));
                startActivity(intent);
            }
        });

        /**
         * banner点击事件
         */
        mImageBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri url = Uri.parse(mAdvertisementBeenList.get(position).getAdvertiseUrl());
                intent.setData(url);
                intent.addCategory(Intent.CATEGORY_DEFAULT);

                Intent newIntent = Intent.createChooser(intent, "选择浏览器");
                startActivity(newIntent);
            }
        });
        mExpertsBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getContext(), DoctorDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(Contract.EXPERT_KEY, mExpertsBean.get(position));
                intent.putExtra("bundle", bundle);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity(),
                            mExpertsBanner, "image").toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });

        /**
         * 自助挂号
         */
        mSelfRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HospitalListActivity.class);
                intent.putExtra(Contract.ADDRESS_KEY, mapTextView.getText());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });

        /**
         * 医疗咨询
         */
        mMedicalConsultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        mHospitalAdapter = new HospitalAdapter(R.layout.hospital_item, null);
        mHomePageRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mHospitalAdapter.bindToRecyclerView(mHomePageRecyclerView);
        mHospitalAdapter.setEmptyView(R.layout.empty_view);
        mPresenter.onRequestData();
    }

    private void initBanner() {

        //设置图片加载器
        mImageBanner.setImageLoader(new GlideImageLoader());
        mExpertsBanner.setImageLoader(new GlideImageLoader());
        //banner设置方法全部调用完毕时最后调用
        mImageBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mExpertsBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        mImageBanner.setDelayTime(5000);
        mExpertsBanner.setDelayTime(8000);

        mImageBanner.start();
        mExpertsBanner.start();
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
    public void updateImages(List<AdvertisementBean> advertisementBeanList) {
        mAdvertisementBeenList = advertisementBeanList;
        Log.d("HomePageFragment", "updateImages" + mAdvertisementBeenList.get(0).getImageUrl());
        mAdvertisementImageUrl.clear();
        for (int i = 0, n = mAdvertisementBeenList.size(); i < n; i++) {
            mAdvertisementImageUrl.add(Contract.AdvertisementBase +
                    mAdvertisementBeenList.get(i).getImageUrl());
        }
        mImageBanner.update(mAdvertisementImageUrl);
    }

    @Override
    public void updateExperts(List<ExpertBean> expertBeenF) {
        mExpertsBean = expertBeenF;
        mExpertsImageUrl.clear();
        for (int i = 0, n = expertBeenF.size(); i < n; i++) {
            mExpertsImageUrl.add(Contract.DoctorBase + expertBeenF.get(i).getImageUrl());
        }
        mExpertsBanner.update(mExpertsImageUrl);
    }

    @Override
    public void initHospitals(List<HospitalBean> hospitalBeanList) {
        //mHospitalAdapter.addData(hospitalBeanList);
    }

    @Override
    public void updateHospitals(List<HospitalBean> hospitalBeanList) {
        /*List<HospitalBean> nowList = mHospitalAdapter.getData();
        for (HospitalBean bean : hospitalBeanList) {
            if (!nowList.contains(bean))
                mHospitalAdapter.addData(bean);
        }*/
        mHospitalAdapter.getData().clear();
        mHospitalAdapter.addData(hospitalBeanList);
        mHospitalAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mImageBanner != null) {
            mImageBanner.stopAutoPlay();
        }
        if (mExpertsBanner != null) {
            mExpertsBanner.stopAutoPlay();
        }
    }

    private void hideKeyBoard() {
        InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(mCardView.getWindowToken(),0);
    }
}
