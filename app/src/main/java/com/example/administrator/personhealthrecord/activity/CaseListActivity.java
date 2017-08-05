package com.example.administrator.personhealthrecord.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.adapter.CaseAdapter;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.CaseBean;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.util.DialogUtil;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Administrator on 2017-8-5.
 */

public class CaseListActivity extends BaseActivity {

    @BindView(R.id.case_list_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.case_list_swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.case_list_fab)
    FloatingActionButton mFloatingActionButton;

    SweetAlertDialog mDialog;
    CaseListService mService;
    Disposable mDisposable;
    CaseAdapter mAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_case_list;
    }

    @Override
    protected void initData() {
        initToolbar("我的病历", true, null);
        mService = RetrofitUtil.getRetrofit().create(CaseListService.class);
        mAdapter = new CaseAdapter(null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setEmptyView(R.layout.empty_view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCaseList();
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCaseList();
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(CaseListActivity.this, CaseDetailActivity.class);
                intent.putExtra("data", mAdapter.getItem(position));
                startActivity(intent);
            }
        });
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaseListActivity.this, AddCaseActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getCaseList() {
        if (Contract.IsLogin.equals(Contract.Login)) {
            mSwipeRefreshLayout.setRefreshing(true);
            mService.getCaseList(Contract.cookie)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(new Function<ResponseBody, List<CaseBean>>() {
                        @Override
                        public List<CaseBean> apply(ResponseBody body) throws Exception {
                            try {
                                JSONObject jsonObject = new JSONObject(body.string());
                                if (jsonObject.get("status").equals("success")) {
                                    Gson gson = new Gson();
                                    List<CaseBean> caseBeanList = gson.fromJson(
                                            jsonObject.get("collection").toString(),
                                            new TypeToken<List<CaseBean>>() {
                                            }.getType());
                                    Collections.sort(caseBeanList);
                                    return caseBeanList;
                                }
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                            return Collections.emptyList();
                        }
                    })
                    .subscribe(new Observer<List<CaseBean>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            mDisposable = d;
                        }

                        @Override
                        public void onNext(List<CaseBean> value) {
                            updateCaseList(value);
                        }

                        @Override
                        public void onError(Throwable e) {
                            mSwipeRefreshLayout.setRefreshing(false);
                            showMessage(mRecyclerView, "连接失败");
                        }

                        @Override
                        public void onComplete() {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    });
        } else {
            mDialog = DialogUtil.getLoginDialog(this);
            mDialog.show();
        }
    }

    private void updateCaseList(List<CaseBean> caseBeanList) {
        mAdapter.getData().clear();
        mAdapter.addData(caseBeanList);
    }

    @Override
    protected void onDestroy() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
        super.onDestroy();
    }

    interface CaseListService {
        @GET("medical_record/medical_record_list")
        Observable<ResponseBody> getCaseList(@Header("Cookie") String cookie);
    }
}
