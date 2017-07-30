package com.example.administrator.personhealthrecord.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.personhealthrecord.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017-7-19.
 */

public abstract class BaseFragment extends Fragment {

    Unbinder mUnbinder;

    Toolbar mToolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("BaseFragment",this.getClass().getSimpleName()+"onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        init();
    }

    private void init() {
        initData();
        initEvent();
    }

    protected abstract void initEvent();

    protected abstract void initData();

    protected void initToolbar(String title) {
        if (mToolbar != null) {
            mToolbar.setTitle(title);
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        }
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null)
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BaseFragment", this.getClass().getSimpleName()+ "onCreate");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("BaseFragment", this.getClass().getSimpleName()+ "onPause");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("BaseFragment", this.getClass().getSimpleName()+ "onStart");
    }

    @Override
    public void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
        Log.d("BaseFragment",this.getClass().getSimpleName()+"onDestroy");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("BaseFragment", this.getClass().getName() + "onAttach");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("BaseFragment", this.getClass().getSimpleName()+ "onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("BaseFragment", this.getClass().getSimpleName()+"onDetach");
    }
}
