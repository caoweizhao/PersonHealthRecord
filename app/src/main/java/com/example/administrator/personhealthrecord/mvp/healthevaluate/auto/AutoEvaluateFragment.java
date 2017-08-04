package com.example.administrator.personhealthrecord.mvp.healthevaluate.auto;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.PHRBean;
import com.example.administrator.personhealthrecord.mvp.healthevaluate.HealthyEvaluatePresenter;
import com.example.administrator.personhealthrecord.mvp.healthevaluate.IHealthyEvaluatePresenter;
import com.example.administrator.personhealthrecord.mvp.healthevaluate.IHealthyEvaluateView;
import com.example.administrator.personhealthrecord.view.WaveImageView;

import butterknife.BindView;

/**
 * Created by andy on 2017/8/3.
 */

public class AutoEvaluateFragment extends IHealthyEvaluateView{
    WaveImageView imageView;
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
        mPhrBodyMassIndexUnit.setText(bean.getBodyMassIndex());
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.auto_evaluate,container,false);
        imageView=(WaveImageView)view.findViewById(R.id.healthy_evaluate_iamge_score);
        imageView.drawCircle();
        imageView.setColor();
        imageView.setHight(0);
        imageView.startAnimate();
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

    public void onPHRScoreReady(int score)
    {
        imageView.SetHightAnimate(score);
    }

}
