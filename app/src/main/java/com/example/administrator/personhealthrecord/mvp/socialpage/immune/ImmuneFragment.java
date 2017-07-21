package com.example.administrator.personhealthrecord.mvp.socialpage.immune;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.mvp.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImmuneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImmuneFragment extends BaseFragment {


    public ImmuneFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_immune_page, container, false);
        return view;
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
