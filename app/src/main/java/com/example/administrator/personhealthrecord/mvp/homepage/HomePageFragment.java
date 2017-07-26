package com.example.administrator.personhealthrecord.mvp.homepage;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.activity.MapAcitvity;
import com.example.administrator.personhealthrecord.adapter.HospitalAdapter;
import com.example.administrator.personhealthrecord.bean.ExpertBean;
import com.example.administrator.personhealthrecord.bean.HospitalBean;
import com.example.administrator.personhealthrecord.mvp.main.MainActivity;
import com.example.administrator.personhealthrecord.others.GlideImageLoader;
import com.example.administrator.personhealthrecord.util.AnimateUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

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
    private SearchView mSearchView;

    private boolean isExpand = false;
    private HospitalAdapter mHospitalAdapter;


    @BindView(R.id.near_by_hospital)
    CardView mCardView;
    @BindView(R.id.map_text_view)
    TextView mpaTextView;
    public HomePageFragment() {
        // Required empty public constructor
    }

    public static HomePageFragment newInstance() {
        return new HomePageFragment();
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
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBanner();
        initToolbar("首页");
        setUpWithActivity(view);

        mPrivateDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimateUtil.createCircularReveal(v);
            }
        });
        mCardView.post(new Runnable() {
            @Override
            public void run() {
                int[] location = new int[2];
                mCardView.getLocationInWindow(location);
                Log.d("HomePageFragment", "location:" + location[0] + ":" + location[1]);
            }
        });

    }

    private void setUpWithActivity(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((MainActivity) getActivity()).setUpWithToolbar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpand) {
                    getActivity().onBackPressed();
                } else {
                    ((MainActivity) getActivity()).openMenu();
                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d("as", "onCreateOptionsMenu: ");
        super.onCreateOptionsMenu(menu, inflater);
        MenuInflater menuInflater = getActivity().getMenuInflater();
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
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint(getResources().getString(R.string.search_hint));
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO: 2017-7-21 搜索事件处理
                Toast.makeText(getContext(), "Submit!", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    protected void initEvent() {
        mpaTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MapAcitvity.class);
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
    }

    @Override
    protected void initData() {
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
    public void updateImages(List<String> urls) {
        mImageBanner.update(urls);
    }

    @Override
    public void updateExperts(List<ExpertBean> expertBeenF) {
        mExpertsBanner.update(expertBeenF);
    }

    @Override
    public void updateHospitals(List<HospitalBean> hospitalBeanList) {
        Log.d("HomePageFragment", "hos:" + hospitalBeanList);
        mHospitalAdapter = new HospitalAdapter(R.layout.hospital_item, hospitalBeanList);
        mHomePageRecyclerView.setAdapter(mHospitalAdapter);
        mHospitalAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                AnimateUtil.createCircularReveal(view);
            }
        });
        mHomePageRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
