package com.example.administrator.personhealthrecord.activity;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.CaseBean;

import butterknife.BindView;

/**
 * Created by Administrator on 2017-8-5.
 */

public class CaseDetailActivity extends BaseActivity {

    CaseBean mCaseBean;
    @BindView(R.id.name_text)
    TextView mNameText;
    @BindView(R.id.age_text)
    TextView mAgeText;
    @BindView(R.id.gender_text)
    TextView mGenderText;
    @BindView(R.id.hospital_text)
    TextView mHospitalText;
    @BindView(R.id.time_text)
    TextView mTimeText;
    @BindView(R.id.condition_text)
    TextView mConditionText;
    @BindView(R.id.prescription_text)
    TextView mPrescriptionText;
    @BindView(R.id.schedule_text)
    TextView mScheduleText;
    @BindView(R.id.remark_text)
    TextView mRemarkText;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_case_details;
    }

    @Override
    protected void initData() {
        initToolbar("病历详情", true, null);

        Intent intent = getIntent();
        if (intent != null) {
            mCaseBean = intent.getParcelableExtra("data");
            if (mCaseBean != null) {
                initValue();
            }
        }
    }

    private void initValue() {


        mNameText.setText(mCaseBean.getName());
        mAgeText.setText(String.valueOf(mCaseBean.getAge()));
        mGenderText.setText(mCaseBean.getGender());
        mHospitalText.setText(mCaseBean.getHospitalName());
        mTimeText.setText(mCaseBean.getDate());
        mConditionText.setText(mCaseBean.getPhysicalCondition());
        mPrescriptionText.setText(mCaseBean.getPrescriptionMedicine());
        mScheduleText.setText(mCaseBean.getTreatmentSchedule());
        mRemarkText.setText(mCaseBean.getTodoSomething());
    }


}
