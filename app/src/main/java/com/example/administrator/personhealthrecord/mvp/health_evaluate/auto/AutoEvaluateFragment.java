package com.example.administrator.personhealthrecord.mvp.health_evaluate.auto;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.SuperscriptSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.PHRBean;
import com.example.administrator.personhealthrecord.mvp.health_evaluate.HealthyEvaluatePresenter;
import com.example.administrator.personhealthrecord.mvp.health_evaluate.IHealthyEvaluatePresenter;
import com.example.administrator.personhealthrecord.mvp.health_evaluate.IHealthyEvaluateView;
import com.example.administrator.personhealthrecord.view.WaveImageView;

import butterknife.BindView;

/**
 * Created by andy on 2017/8/3.
 */

public class AutoEvaluateFragment extends IHealthyEvaluateView {
    WaveImageView mWaveImageView;
    @BindView(R.id.phr_body_mass_index)
    TextView mPhrBodyMassIndex;
    @BindView(R.id.phr_tall)
    TextView mPhrTall;
    @BindView(R.id.phr_weight)
    TextView mPhrWeight;
    @BindView(R.id.blood_type)
    TextView mBloodType;
    @BindView(R.id.heart_rate)
    TextView mHeartRate;
    @BindView(R.id.phr_systolic)
    TextView mPressureUp;
    @BindView(R.id.phr_diastolic)
    TextView mPressureDown;
    @BindView(R.id.phr_smoking_amount)
    TextView mPhrSmokingAmount;
    @BindView(R.id.drinking_type)
    TextView mDrinkingType;
    @BindView(R.id.drinking_amount)
    TextView mDrinkingAmount;
    @BindView(R.id.drinking_frequency)
    TextView mDrinkingFrequency;
    @BindView(R.id.phr_exercise_status)
    TextView mPhrExerciseStatus;
    @BindView(R.id.phr_allergy_history)
    TextView mPhrAllergyHistory;
    @BindView(R.id.phr_body_mass_index_unit)
    TextView mPhrBodyMassIndexUnit;

    @Override
    public void OnPHRReady(PHRBean bean) {
        mPresenter.getPHRScore(bean);
        mPhrBodyMassIndex.setText(bean.getBodyMassIndex());
        mPhrTall.setText(bean.getHeight());
        mPhrWeight.setText(bean.getWeight());
        mBloodType.setText(bean.getBloodType());
        mHeartRate.setText(bean.getHeartRate());
        mPressureUp.setText(bean.getBloodPressureUp());
        mPressureDown.setText(bean.getBloodPressureDown());
        mPhrSmokingAmount.setText(bean.getSmokingVolume());
        mDrinkingType.setText(bean.getDrinkingType());
        mDrinkingAmount.setText(bean.getAlcoholVolume());
        mDrinkingFrequency.setText(bean.getDrinkingFrequency());
        mPhrExerciseStatus.setText(bean.getPhysicalExercise());
        mPhrAllergyHistory.setText(bean.getMedicineAllergy());

        SpannableString ss = new SpannableString("kg/m2");
        SuperscriptSpan sss = new SuperscriptSpan();
        ss.setSpan(sss, 4, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        mPhrBodyMassIndexUnit.setText(ss);
    }

    @Override
    public void OnPHRScoreReady(int score) {
        mWaveImageView.setHeightAnimate(score);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.auto_evaluate, container, false);
        mWaveImageView = (WaveImageView) view.findViewById(R.id.manual_evaluate_iamge_score);
        mWaveImageView.drawCircle();
        mWaveImageView.setColor();
        mWaveImageView.setValue(0);
        mWaveImageView.startAnimate();
        mPresenter.getPHRdata();
        return view;
    }

    @Override
    public IHealthyEvaluatePresenter createPresenter() {
        return new HealthyEvaluatePresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
    }
}
