package com.example.administrator.personhealthrecord.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.adapter.ExpertAdapter;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.ExpertBean;
import com.example.administrator.personhealthrecord.contract.Contract;
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

/**
 * Created by Administrator on 2017-7-29.
 */

public class ExpertListActivity extends BaseActivity {

    @BindView(R.id.expert_list_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    ExpertAdapter mAdapter;
    private String mHospitalId;

    private Disposable mDisposable;
    ExpertService mService;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_expert_list_layout;
    }

    @Override
    protected void initData() {
        mAdapter = new ExpertAdapter(R.layout.expert_item_layout, null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setEmptyView(R.layout.empty_view);

        initToolbar("医生列表", true, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mService = RetrofitUtil.getRetrofit().create(ExpertService.class);
        Intent intent = getIntent();
        mHospitalId = intent.getStringExtra(Contract.HOSPITAL_KEY);
        if (mHospitalId != null) {
            getExpertList();
        }

    }

    @Override
    protected void initEvents() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getExpertList();
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ExpertBean expertBean = (ExpertBean) adapter.getItem(position);
                Intent intent = new Intent(ExpertListActivity.this, SelfRegisterActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(Contract.EXPERT_KEY, expertBean);
                intent.putExtra("bundle", bundle);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(ExpertListActivity.this,
                            Pair.create(view.findViewById(R.id.expert_item_img), "doctor_pic")).toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });
    }

    private void getExpertList() {
        mService.getExperts(mHospitalId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ResponseBody, List<ExpertBean>>() {
                    @Override
                    public List<ExpertBean> apply(ResponseBody body) throws Exception {
                        JSONObject jsonObject = new JSONObject(body.string());
                        if (jsonObject.get("status").equals("success")) {
                            Gson gson = new Gson();
                            List<ExpertBean> expertBeanList = gson.fromJson(
                                    jsonObject.get("collection").toString(),
                                    new TypeToken<List<ExpertBean>>() {
                                    }.getType());
                            return expertBeanList;
                        }
                        return Collections.emptyList();
                    }
                }).subscribe(new Observer<List<ExpertBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(List<ExpertBean> value) {
                updateExperts(value);
            }

            @Override
            public void onError(Throwable e) {
                mSwipeRefreshLayout.setRefreshing(false);
                showMessage(mRecyclerView, e.getMessage());
            }

            @Override
            public void onComplete() {
                mSwipeRefreshLayout.setRefreshing(false);
                showMessage(mRecyclerView, "更新成功！");
            }
        });
    }

    private void updateExperts(List<ExpertBean> expertBeenList) {
        mAdapter.getData().clear();
        mAdapter.addData(expertBeenList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    interface ExpertService {
        @GET("doctor/find_by_hospital_id/{hospital_id}")
        Observable<ResponseBody> getExperts(@Path("hospital_id") String hospitalId);
    }
}
