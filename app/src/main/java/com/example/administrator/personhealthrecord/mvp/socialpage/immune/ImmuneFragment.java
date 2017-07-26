package com.example.administrator.personhealthrecord.mvp.socialpage.immune;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.mvp.socialpage.SocialPageBaseFragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImmuneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImmuneFragment extends SocialPageBaseFragment<ImmuneService> {

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.social_child_fragment, container, false);
    }

    @Override
    protected int getLayoutRes() {
        return 0;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void fetchData() {
        mSwipeRefreshLayout.setRefreshing(true);

    }

    @Override
    protected void fetchDataDone(List datas) {

    }

    @Override
    protected void loadMoreData() {

    }

    @Override
    protected void loadMoreDataDone(List datas) {

    }
}
