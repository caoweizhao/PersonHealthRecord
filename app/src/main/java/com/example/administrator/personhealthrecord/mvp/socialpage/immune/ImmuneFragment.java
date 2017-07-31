package com.example.administrator.personhealthrecord.mvp.socialpage.immune;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.ImmuneBean;
import com.example.administrator.personhealthrecord.mvp.socialpage.SocialPageBaseFragment;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImmuneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImmuneFragment extends SocialPageBaseFragment<ImmuneBean, ImmuneService> {

    private Disposable mDisposable;

    public ImmuneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("ImmuneFragment", "onResume");
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SocialPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImmuneFragment newInstance() {
        return new ImmuneFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.social_child_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void refreshData() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    protected void loadMoreData() {

    }

    @Override
    protected void loadMoreDataDone(List<ImmuneBean> datas) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initDataDone(List<ImmuneBean> datas) {

    }
}
