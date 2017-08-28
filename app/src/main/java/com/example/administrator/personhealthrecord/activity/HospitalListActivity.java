package com.example.administrator.personhealthrecord.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.adapter.HospitalAdapter;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.HospitalBean;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.util.AnimateUtil;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class HospitalListActivity extends BaseActivity {

    @BindView(R.id.hospital_list_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    HospitalAdapter mAdapter;
    private String mAddress;
    private HospitalService mService;
    Disposable mDisposable;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_hospital_list;
    }

    @Override
    protected void initData() {

        mService = RetrofitUtil.getRetrofit().create(HospitalService.class);
        mAdapter = new HospitalAdapter(R.layout.hospital_item, null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setEmptyView(R.layout.empty_view);
        initToolbar("医院列表", true, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        mAddress = intent.getStringExtra(Contract.ADDRESS_KEY);
        if (!TextUtils.isEmpty(mAddress)) {
            mSwipeRefreshLayout.setRefreshing(true);
            getHospital(mAddress);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRecyclerView.setScaleX(0);
        mRecyclerView.setScaleY(0);
        AnimateUtil.scaleShow(mRecyclerView, null);
    }

    /**
     * 根据地址获取医院数据
     */
    private void getHospital(String address) {
        mService.getHospitals(address)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ResponseBody, List<HospitalBean>>() {
                    @Override
                    public List<HospitalBean> apply(ResponseBody body) throws Exception {

                        JSONObject jsonObject = new JSONObject(body.string());
                        if (jsonObject.get("status").equals("success")) {
                            Gson gson = new Gson();
                            List<HospitalBean> hospitalBeanList = gson.fromJson(
                                    jsonObject.get("collection").toString(),
                                    new TypeToken<List<HospitalBean>>() {
                                    }.getType());
                            return hospitalBeanList;
                        } else {
                            showMessage(mRecyclerView, jsonObject.get("message").toString());
                        }
                        return Collections.emptyList();

                    }
                }).subscribe(new Observer<List<HospitalBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(List<HospitalBean> value) {
                updateHospitals(value);
            }

            @Override
            public void onError(Throwable e) {
                mSwipeRefreshLayout.setRefreshing(false);
                showMessage(mRecyclerView, "获取数据失败，请稍后重试！");
            }

            @Override
            public void onComplete() {
                mSwipeRefreshLayout.setRefreshing(false);
                showMessage(mRecyclerView, "获取成功！");
            }
        });
    }

    private void updateHospitals(List<HospitalBean> hospitalBeanList) {
        mAdapter.getData().clear();
        mAdapter.addData(hospitalBeanList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initEvents() {
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                HospitalBean hospitalBean = (HospitalBean) adapter.getItem(position);
                Intent intent = new Intent(HospitalListActivity.this, ExpertListActivity.class);
                intent.putExtra(Contract.HOSPITAL_KEY, String.valueOf(hospitalBean.getId()));
                startActivity(intent);

            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getHospital(mAddress);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    interface HospitalService {
        @GET("hospital/find_by/{address}")
        Observable<ResponseBody> getHospitals(@Path("address") String address);
    }
}
