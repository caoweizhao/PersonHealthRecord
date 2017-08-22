package com.example.administrator.personhealthrecord.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.adapter.SearchAdapter;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.ExpertBean;
import com.example.administrator.personhealthrecord.bean.HospitalBean;
import com.example.administrator.personhealthrecord.bean.MedicineBean;
import com.example.administrator.personhealthrecord.bean.SearchBean;
import com.example.administrator.personhealthrecord.bean.SearchSection;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.util.DialogUtil;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;
import com.example.administrator.personhealthrecord.util.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
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
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017-8-7.
 */

public class SearchResultActivity extends BaseActivity {

    @BindView(R.id.search_result_recycler_view)
    RecyclerView mRecyclerView;
    SearchAdapter mAdapter;
    SearchService mService;

    List<SearchSection> mMedicineSections = new ArrayList<>();
    List<SearchSection> mSearchSections = new ArrayList<>();
    List<SearchSection> mExpertSections = new ArrayList<>();
    List<SearchSection> mHospitalSections = new ArrayList<>();

    Disposable mDisposable;

    SweetAlertDialog mLoadingDialog;
    String mQuery;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search_result;
    }

    @Override
    protected void initData() {
        mService = RetrofitUtil.getRetrofit().create(SearchService.class);
        mAdapter = new SearchAdapter(this, R.layout.search_item, R.layout.search_section_header, mSearchSections);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setEmptyView(R.layout.empty_view);
        mLoadingDialog = DialogUtil.getLoadingDialog(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar("搜索结果", true, null);
        mQuery = getIntent() != null ? getIntent().getStringExtra("data") : "";
        mAdapter.setQuery(mQuery);
        if (!TextUtils.isEmpty(mQuery)) {
            mLoadingDialog.show();
            mService.doSearch(mQuery)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(new Function<ResponseBody, List<SearchSection>>() {
                        @Override
                        public List<SearchSection> apply(ResponseBody body) throws Exception {
                            try {
                                JSONObject jsonObject = new JSONObject(body.string());
                                if (jsonObject.get("status").equals("success")) {
                                    Gson gson = new Gson();
                                    List<ExpertBean> expertBeanList = gson.fromJson(
                                            jsonObject.get("doctor").toString(),
                                            new TypeToken<List<ExpertBean>>() {
                                            }.getType());
                                    List<HospitalBean> hospitalBeanList = gson.fromJson(
                                            jsonObject.get("hospital").toString(),
                                            new TypeToken<List<HospitalBean>>() {
                                            }.getType());
                                    List<MedicineBean> medicineBeenList = gson.fromJson(
                                            jsonObject.get("medicine").toString(),
                                            new TypeToken<List<MedicineBean>>() {
                                            }.getType());
                                    mSearchSections.add(new SearchSection(true, "医院列表：" + hospitalBeanList.size() + "条记录"));
                                    for (HospitalBean hospital : hospitalBeanList) {
                                        mHospitalSections.add(new SearchSection(hospital));
                                    }
                                    mSearchSections.addAll(mHospitalSections);
                                    mSearchSections.add(new SearchSection(true, "医生列表：" + expertBeanList.size() + "条记录"));
                                    for (ExpertBean expert : expertBeanList) {
                                        mExpertSections.add(new SearchSection(expert));
                                    }
                                    mSearchSections.addAll(mExpertSections);
                                    mSearchSections.add(new SearchSection(true, "药物列表：" + medicineBeenList.size() + "条记录"));
                                    for (MedicineBean medicine : medicineBeenList) {
                                        mMedicineSections.add(new SearchSection(medicine));
                                    }
                                    mSearchSections.addAll(mMedicineSections);
                                    return mSearchSections;
                                } else {
                                    ToastUtil.Toast(jsonObject.get("message").toString());
                                }
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                            return mSearchSections;
                        }
                    })
                    .subscribe(new Observer<List<SearchSection>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            mDisposable = d;
                        }

                        @Override
                        public void onNext(List<SearchSection> value) {
                            updateValue();
                        }

                        @Override
                        public void onError(Throwable e) {
                            ToastUtil.Toast("连接失败");
                            mLoadingDialog.dismiss();
                        }

                        @Override
                        public void onComplete() {
                            mLoadingDialog.dismiss();
                        }
                    });
        }
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SearchSection section = ((SearchSection) adapter.getItem(position));
                if (!section.isHeader) {
                    if (section.t.getType() == SearchBean.TYPE_DOCTOR) {
                        ExpertBean expert = ((ExpertBean) section.t);
                        Intent intent = new Intent(SearchResultActivity.this, DoctorDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(Contract.EXPERT_KEY, expert);
                        intent.putExtra("bundle", bundle);
                        startActivity(intent);
                    } else if (section.t.getType() == SearchBean.TYPE_MEDICINE) {
                        MedicineBean medicine = ((MedicineBean) section.t);
                        Intent intent = new Intent(SearchResultActivity.this, MedicineDetailActivity.class);
                        intent.putExtra("data", medicine);
                        startActivity(intent);
                    } else if (section.t.getType() == SearchBean.TYPE_HOSPITAL) {
                        HospitalBean hospital = ((HospitalBean) section.t);
                        Intent intent = new Intent(SearchResultActivity.this, HospitalDetailActivity.class);
                        intent.putExtra("data", hospital);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private void updateValue() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
        super.onDestroy();
    }

    interface SearchService {
        @GET("hospital/search_hdd")
        Observable<ResponseBody> doSearch(@Query("value") String value);
    }
}
