package com.example.administrator.personhealthrecord.mvp.socialpage.medical;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.adapter.AbstractItemAdapter;
import com.example.administrator.personhealthrecord.bean.MedicineInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MedicalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MedicalFragment extends AMedicalFragment {

    @BindView(R.id.medical_recyclerView)
    RecyclerView mRecyclerView;
    Unbinder mUnbinder;
    public MedicalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SocialPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MedicalFragment newInstance() {
        return new MedicalFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medical_page, container, false);
        mUnbinder = ButterKnife.bind(this,view);
        mPresenter.onRequestData();
        return view;
    }

    @Override
    public AMedicalPresenter createPresenter() {
        return new MedicalPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("MedicalFragment","onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mUnbinder != null){
            mUnbinder.unbind();
        }
    }

    @Override
    public void updateMedicines(List<MedicineInfo> medicineInfoList) {
        AbstractItemAdapter<MedicineInfo> adapter = new AbstractItemAdapter<MedicineInfo>(R.layout.fragment_medical_page, medicineInfoList, this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
