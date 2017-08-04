package com.example.administrator.personhealthrecord.mvp.healthevaluate.manual;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class ManualEvaluateFragment extends IHealthyEvaluateView {
    @BindView(R.id.healthy_evaluate_iamge_score)
    WaveImageView healthyEvaluateIamgeScore;
    @BindView(R.id.manual_evaluate_height)
    EditText manualEvaluateHeight;
    @BindView(R.id.manual_evaluate_hearth_frequent)
    EditText manualEvaluate;
    @BindView(R.id.manual_evaluate_blood_type)
    Spinner manualEvaluateBloodType;
    @BindView(R.id.manual_evaluate_heart_frequent)
    EditText manualEvaluateHeartFrequent;
    @BindView(R.id.manual_evaluate_shrink_press)
    EditText manualEvaluateShrinkPress;
    @BindView(R.id.manual_evaluate_strench_press)
    EditText manualEvaluateStrenchPress;
    @BindView(R.id.manual_evaluate_smoke_value)
    EditText manualEvaluateSmokeValue;
    @BindView(R.id.manual_evaluate_drink_type)
    Spinner manualEvaluateDrinkType;
    @BindView(R.id.manual_evaluate_drink_value)
    EditText manualEvaluateDrinkValue;
    @BindView(R.id.manual_evaluate_drik_frequnt)
    Spinner manualEvaluateDrikFrequnt;
    @BindView(R.id.manual_evaluate_exersice)
    EditText manualEvaluateExersice;
    @BindView(R.id.manual_evaluate_Drug_allergy)
    EditText manualEvaluateDrugAllergy;
    @BindView(R.id.button)
    Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hm_evaluate, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        healthyEvaluateIamgeScore.drawCircle();
        healthyEvaluateIamgeScore.setColor();
        healthyEvaluateIamgeScore.setHight(0);
        healthyEvaluateIamgeScore.startAnimate();
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
    public void setMenuVisibility(boolean menuVisible) {
    }

    @Override
    public void OnPHRReady(PHRBean bean) {

    }

}
