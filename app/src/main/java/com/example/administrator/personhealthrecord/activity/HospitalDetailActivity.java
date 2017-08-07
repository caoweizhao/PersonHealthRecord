package com.example.administrator.personhealthrecord.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.HospitalBean;
import com.example.administrator.personhealthrecord.contract.Contract;

import butterknife.BindView;

/**
 * Created by Administrator on 2017-8-6.
 */

public class HospitalDetailActivity extends BaseActivity {

    HospitalBean mHospitalBean;
    @BindView(R.id.hospital_detail_image)
    ImageView mHospitalDetailImage;
    @BindView(R.id.hospital_name)
    TextView mHospitalName;
    @BindView(R.id.english_name)
    TextView mEnglishName;
    @BindView(R.id.hospital_category)
    TextView mHospitalCategory;
    @BindView(R.id.hospital_class)
    TextView mHospitalClass;
    @BindView(R.id.hospital_address)
    TextView mHospitalAddress;
    @BindView(R.id.hospital_summary)
    TextView mHospitalSummary;
    @BindView(R.id.hospital_scale)
    TextView mHospitalScale;
    @BindView(R.id.hospital_history)
    TextView mHospitalHistory;
    @BindView(R.id.hospital_department_status)
    TextView mHospitalDepartmentStatus;
    @BindView(R.id.hospital_medical_team)
    TextView mHospitalMedicalTeam;
    @BindView(R.id.hospital_medical_equipment)
    TextView mHospitalMedicalEquipment;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_hospital_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sm.setStatusBarTintEnabled(false);
    }

    @Override
    protected void initData() {
        initToolbar("医院详情", true, null);

        Intent intent = getIntent();
        if (intent != null) {
            mHospitalBean = intent.getParcelableExtra("data");
            if (mHospitalBean != null) {
                initValue();
            }
        }
    }

    private void initValue() {
        String imageUrl;
        if (mHospitalBean.getImageUrl().contains("http:")) {
            imageUrl = mHospitalBean.getImageUrl();
        } else {
            imageUrl = Contract.HospitalBase + mHospitalBean.getImageUrl();
        }
        Glide.with(this)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(mHospitalDetailImage);

        setText(mHospitalName,mHospitalBean.getName());
        setText(mEnglishName,mHospitalBean.getEnglishName());
        setText(mHospitalCategory,mHospitalBean.getCategory());
        setText(mHospitalClass,mHospitalBean.getLevel());
        setText(mHospitalAddress,mHospitalBean.getAddress());
        setText(mHospitalSummary,mHospitalBean.getSummary());
        setText(mHospitalScale,mHospitalBean.getScale());
        setText(mHospitalHistory,mHospitalBean.getHistory());
        setText(mHospitalDepartmentStatus,mHospitalBean.getDepartmentStatus());
        setText(mHospitalMedicalTeam,mHospitalBean.getMedicalTeam());
        setText(mHospitalMedicalEquipment,mHospitalBean.getMedicalEquipment());
    }

    private void setText(TextView textView, String text) {
        if (!TextUtils.isEmpty(text)) {
            textView.setText(text);
        }
    }
}
