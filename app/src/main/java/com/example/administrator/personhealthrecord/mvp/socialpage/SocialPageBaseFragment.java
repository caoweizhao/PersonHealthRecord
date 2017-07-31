package com.example.administrator.personhealthrecord.mvp.socialpage;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.adapter.AbstractItemAdapter;
import com.example.administrator.personhealthrecord.bean.AbstractItem;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017-7-25.
 */

public abstract class SocialPageBaseFragment<B extends AbstractItem, T> extends Fragment {

    @BindView(R.id.social_child_fragment_swipeRefreshLayout)
    public SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.social_child_fragment_recyclerView)
    public RecyclerView mRecyclerView;

    private Unbinder mUnbinder;
    protected AbstractItemAdapter<B> mAdapter;
    protected Retrofit mRetrofit;
    protected T mService;
    protected List<B> mDataList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutRes(), container, false);
    }

    protected abstract
    @LayoutRes
    int getLayoutRes();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
        mRetrofit = RetrofitUtil.getRetrofit();
        initView();
        initEvent();
        mService = createService();
        initData();
    }

    protected abstract void initData();

    protected void initEvent() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.setEnableLoadMore(false);
                refreshData();
            }
        });

        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMoreData();
            }
        }, mRecyclerView);
    }


    protected abstract void refreshData();

    private void initView() {
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.google_play_yellow),
                getResources().getColor(R.color.google_play_blue),
                getResources().getColor(R.color.google_play_red),
                getResources().getColor(R.color.google_play_green));
        mAdapter = new AbstractItemAdapter(R.layout.social_child_fragment, mDataList, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setEmptyView(R.layout.empty_view);
    }

    /**
     * 解绑
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }


    protected void initDataDone(List<B> datas) {
        Log.d("SocialPageBaseFragment","initDataDone"+getClass().getName());
        Collections.sort(datas);
        Collections.reverse(datas);
        for (B data : datas) {
            if (!mDataList.contains(data)) {
                mAdapter.addData(0, data);
            }
        }
        mRecyclerView.scrollToPosition(0);
    }

    protected void refreshDataDone(List<B> dataList) {
        mAdapter.addData(0, dataList);
        mRecyclerView.scrollToPosition(0);
    }

    /**
     * 加载更多数据
     */
    protected abstract void loadMoreData();

    /**
     * 加载更多数据完毕
     *
     * @param datas 加载的更多数据
     */
    protected void loadMoreDataDone(List<B> datas) {
        int mDataCount = mDataList.size();
        mAdapter.addData(mDataCount, datas);
    }

    /**
     * 创建对应Fragment的请求接口
     */
    protected T createService() {
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[1];
       /* Type t = SocialPageBaseFragment.class.getGenericSuperclass();
        Type[] params = ((ParameterizedType) t).getActualTypeArguments();
        Class<T> cls = (Class<T>) params[0];*/
        Log.d("SocialPageBaseFragment", "class:" + entityClass);
        return mRetrofit.create(entityClass);
    }

    Snackbar snackbar;

    /**
     * SnackBar显示提示
     *
     * @param message 提示信息
     */
    protected void showMessage(String message) {
        View view = ((SocialPageFragment) getParentFragment()).mCollapsingToolbarLayout;
        if (snackbar == null) {
            snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
                    .setAction("我知道了", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                        }
                    });
            snackbar.show();
        } else {
            snackbar.setText(message);
            snackbar.show();
        }
    }

}
