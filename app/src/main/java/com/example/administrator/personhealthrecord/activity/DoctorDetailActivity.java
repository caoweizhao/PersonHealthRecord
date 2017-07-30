package com.example.administrator.personhealthrecord.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.ExpertBean;
import com.example.administrator.personhealthrecord.contract.Contract;

import butterknife.BindView;

/**
 * Created by Administrator on 2017-7-30.
 */

public class DoctorDetailActivity extends BaseActivity {

    @BindView(R.id.doctor_pic)
    ImageView mDoctorPic;
    @BindView(R.id.doctor_name)
    TextView mDoctorName;
    @BindView(R.id.doctor_position)
    TextView mDoctorPosition;
    @BindView(R.id.doctor_workplace)
    TextView mDoctorWorkplace;
    @BindView(R.id.doctor_details)
    TextView mDoctorDetails;
    private ExpertBean mExpertBean;

    @Override
    protected int getLayoutRes() {
        return R.layout.doctor_info_layout;
    }

    @Override
    protected void initData() {
        initToolbar("医生详情", true, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra("bundle");
            if (bundle != null) {
                mExpertBean = bundle.getParcelable(Contract.EXPERT_KEY);
                initValue();
            }
        }
    }

    private void initValue() {
        String docImageUrl = Contract.DoctorBase + mExpertBean.getImageUrl();
        Glide.with(this)
                .load(docImageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mDoctorPic);
        mDoctorName.setText(mExpertBean.getName());
        mDoctorDetails.setText(mExpertBean.getQualification());
        mDoctorPosition.setText(mExpertBean.getDoctorTitle());
        mDoctorWorkplace.setText(mExpertBean.getAddress());
    }
}
