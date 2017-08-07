package com.example.administrator.personhealthrecord.mvp.healthevaluate.manual;

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
import com.example.administrator.personhealthrecord.mvp.healthevaluate.HealthyEvaluatePresenter;
import com.example.administrator.personhealthrecord.mvp.healthevaluate.IHealthyEvaluatePresenter;
import com.example.administrator.personhealthrecord.mvp.healthevaluate.IHealthyEvaluateView;
import com.example.administrator.personhealthrecord.view.WaveImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by andy on 2017/8/3.
 */

public class ManualEvaluateFragment extends IHealthyEvaluateView {
    @BindView(R.id.manual_evaluate_iamge_score)
    WaveImageView healthyEvaluateIamgeScore;


    @BindView(R.id.edit_tall)
    EditText editTall;
    @BindView(R.id.edit_weight)
    EditText editWeight;
    @BindView(R.id.blood_type)
    TextView bloodType;
    @BindView(R.id.blood_type_spinner)
    Spinner bloodTypeSpinner;
    @BindView(R.id.edit_heart_rate)
    EditText editHeartRate;
    @BindView(R.id.blood_pressure)
    TextView bloodPressure;
    @BindView(R.id.edit_systolic)
    EditText editSystolic;
    @BindView(R.id.edit_diastolic)
    EditText editDiastolic;
    @BindView(R.id.smoking_amount_spinner)
    Spinner smokingAmountSpinner;
    @BindView(R.id.drinking_type)
    TextView drinkingType;
    @BindView(R.id.drinking_type_spinner)
    Spinner drinkingTypeSpinner;
    @BindView(R.id.alcohol_comsuption_spinner)
    Spinner alcoholComsuptionSpinner;
    @BindView(R.id.drinking_frequency_spinner)
    Spinner drinkingFrequencySpinner;
    @BindView(R.id.exercise_status)
    TextView exerciseStatus;
    @BindView(R.id.edit_exercise_status)
    EditText editExerciseStatus;
    @BindView(R.id.commit_ok)
    Button commitOk;
    @BindView(R.id.cardView)
    CardView cardView;
    Unbinder unbinder1;
    @BindView(R.id.phr_allergy_history)
    EditText phrAllergyHistory;
    Unbinder unbinder;
    private PHRBean mPHRBean;

    SweetAlertDialog mDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hm_evaluate, container, false);
        unbinder = ButterKnife.bind(this, view);
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
        commitOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPHRBean.setHeight(editTall.getText().toString());
                mPHRBean.setWeight(editWeight.getText().toString());
                mPHRBean.setBloodPressureUp(editSystolic.getText().toString());
                mPHRBean.setBloodPressureDown(editDiastolic.getText().toString());
                mPHRBean.setHeartRate(editHeartRate.getText().toString());
                mPHRBean.setMedicineAllergy(phrAllergyHistory.getText().toString());
                mPHRBean.setPhysicalExercise(editExerciseStatus.getText().toString());
//                mDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
//                mDialog.setCancelable(false);
//                mDialog.setTitleText("正在提交，请稍后...");
//                mDialog.show();
                mPresenter.getPHRScore(mPHRBean);
            }
        });

        bloodTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        drinkingTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        drinkingFrequencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        alcoholComsuptionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        smokingAmountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
    }

    @Override
    public void OnPHRReady(PHRBean bean) {

    }

    @Override
    public void OnPHRScoreReady(int score) {
        healthyEvaluateIamgeScore.SetHightAnimate(score);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
