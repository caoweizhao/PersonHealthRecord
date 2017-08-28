package com.example.administrator.personhealthrecord.mvp.health_evaluate.manual;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.PHRBean;
import com.example.administrator.personhealthrecord.mvp.health_evaluate.HealthyEvaluatePresenter;
import com.example.administrator.personhealthrecord.mvp.health_evaluate.IHealthyEvaluatePresenter;
import com.example.administrator.personhealthrecord.mvp.health_evaluate.IHealthyEvaluateView;
import com.example.administrator.personhealthrecord.view.WaveImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by andy on 2017/8/3.
 */

public class ManualEvaluateFragment extends IHealthyEvaluateView {
    @BindView(R.id.manual_evaluate_iamge_score)
    WaveImageView mHealthyEvaluateImageScore;
    @BindView(R.id.edit_tall)
    EditText mEditTall;
    @BindView(R.id.edit_weight)
    EditText mEditWeight;
    @BindView(R.id.blood_type)
    TextView mBloodType;
    @BindView(R.id.blood_type_spinner)
    Spinner mBloodTypeSpinner;
    @BindView(R.id.edit_heart_rate)
    EditText mEditHeartRate;
    @BindView(R.id.blood_pressure)
    TextView mBloodPressure;
    @BindView(R.id.edit_systolic)
    EditText mEditSystolic;
    @BindView(R.id.edit_diastolic)
    EditText mEditDiastolic;
    @BindView(R.id.smoking_amount_spinner)
    Spinner mSmokingAmountSpinner;
    @BindView(R.id.drinking_type)
    TextView mDrinkingType;
    @BindView(R.id.drinking_type_spinner)
    Spinner mDrinkingTypeSpinner;
    @BindView(R.id.alcohol_consumption_spinner)
    Spinner mAlcoholConsumptionSpinner;
    @BindView(R.id.drinking_frequency_spinner)
    Spinner mDrinkingFrequencySpinner;
    @BindView(R.id.exercise_status)
    TextView mExerciseStatus;
    @BindView(R.id.edit_exercise_status)
    EditText mEditExerciseStatus;
    @BindView(R.id.commit_ok)
    Button mCommitOk;
    @BindView(R.id.cardView)
    CardView mCardView;
    @BindView(R.id.phr_allergy_history)
    EditText phrAllergyHistory;
    Unbinder unbinder;
    private PHRBean mPHRBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hm_evaluate, container, false);
        unbinder = ButterKnife.bind(this, view);
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
        mCommitOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPHRBean.setHeight(mEditTall.getText().toString());
                mPHRBean.setWeight(mEditWeight.getText().toString());
                mPHRBean.setBloodPressureUp(mEditSystolic.getText().toString());
                mPHRBean.setBloodPressureDown(mEditDiastolic.getText().toString());
                mPHRBean.setHeartRate(mEditHeartRate.getText().toString());
                mPHRBean.setMedicineAllergy(phrAllergyHistory.getText().toString());
                mPHRBean.setPhysicalExercise(mEditExerciseStatus.getText().toString());
                mPresenter.getPHRScore(mPHRBean);
            }
        });

        mBloodTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mPHRBean.setBloodType("A型");
                        break;
                    case 1:
                        mPHRBean.setBloodType("B型");
                        break;
                    case 2:
                        mPHRBean.setBloodType("AB型");
                        break;
                    case 3:
                        mPHRBean.setBloodType("O型");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mDrinkingTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mPHRBean.setDrinkingType("白酒");
                        break;
                    case 1:
                        mPHRBean.setDrinkingType("红酒");
                        break;
                    case 2:
                        mPHRBean.setDrinkingType("啤酒");
                        break;
                    case 3:
                        mPHRBean.setDrinkingType("药酒");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mDrinkingFrequencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mPHRBean.setDrinkingFrequency("从不");
                        break;
                    case 1:
                        mPHRBean.setDrinkingFrequency("偶尔");
                        break;
                    case 2:
                        mPHRBean.setDrinkingFrequency("经常");
                        break;
                    case 3:
                        mPHRBean.setDrinkingFrequency("总是");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mAlcoholConsumptionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mPHRBean.setAlcoholVolume("从不");
                        break;
                    case 1:
                        mPHRBean.setAlcoholVolume("适当");
                        break;
                    case 2:
                        mPHRBean.setAlcoholVolume("中等");
                        break;
                    case 3:
                        mPHRBean.setAlcoholVolume("大量");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSmokingAmountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mPHRBean.setSmokingVolume("从不");
                        break;
                    case 1:
                        mPHRBean.setSmokingVolume("少量");
                        break;
                    case 2:
                        mPHRBean.setSmokingVolume("中等");
                        break;
                    case 3:
                        mPHRBean.setSmokingVolume("大量");
                        break;
                    case 4:
                        mPHRBean.setSmokingVolume("上瘾");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void initData() {
        mPHRBean = new PHRBean();
        mHealthyEvaluateImageScore.drawCircle();
        mHealthyEvaluateImageScore.setColor();
        mHealthyEvaluateImageScore.setValue(0);
        mHealthyEvaluateImageScore.startAnimate();
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
    }

    @Override
    public void OnPHRReady(PHRBean bean) {

    }

    @Override
    public void OnPHRScoreReady(int score) {
        mHealthyEvaluateImageScore.setHeightAnimate(score);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
