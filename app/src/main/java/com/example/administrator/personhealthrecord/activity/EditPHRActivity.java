package com.example.administrator.personhealthrecord.activity;

import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.SuperscriptSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.PHRBean;
import com.example.administrator.personhealthrecord.bean.UserInfoBean;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;

/**
 * Created by Administrator on 2017-8-1.
 */

public class EditPHRActivity extends BaseActivity {

    PHRBean mPHRBean;
    UserInfoBean mUserInfoBean;
    @BindView(R.id.edit_tall)
    EditText mEditTall;
    @BindView(R.id.edit_weight)
    EditText mEditWeight;
    @BindView(R.id.blood_type_spinner)
    Spinner mBloodTypeSpinner;
    @BindView(R.id.edit_heart_rate)
    EditText mEditHeartRate;
    @BindView(R.id.edit_systolic)
    EditText mEditPressureUP;
    @BindView(R.id.edit_diastolic)
    EditText mEditPressureDown;
    @BindView(R.id.smoking_amount_spinner)
    Spinner mSmokingAmountSpinner;
    @BindView(R.id.drinking_type_spinner)
    Spinner mDrinkingTypeSpinner;
    @BindView(R.id.alcohol_consumption_spinner)
    Spinner mAlcoholConsumptionSpinner;
    @BindView(R.id.drinking_frequency_spinner)
    Spinner mDrinkingFrequencySpinner;
    @BindView(R.id.edit_exercise_status)
    EditText mEditExerciseStatus;
    @BindView(R.id.edit_allergy_history)
    EditText mEditAllergyHistory;
    @BindView(R.id.commit_ok)
    Button mCommitOk;
    @BindView(R.id.phr_name)
    TextView mPhrName;
    @BindView(R.id.phr_age)
    TextView mPhrAge;
    @BindView(R.id.phr_body_mass_index)
    TextView mPhrBodyMassIndex;
    @BindView(R.id.phr_body_mass_index_unit)
    TextView mPhrBodyMassIndexUnit;
    /**
     * 使用map存储spinner下标
     */
    Map<String, Integer> mSpinnerIndex = new HashMap<>();
    EditPHRService mService;
    Disposable mDisposable;
    public static final int BLOOD_TYPE_A = 0;
    public static final int BLOOD_TYPE_B = 1;
    public static final int BLOOD_TYPE_AB = 2;
    public static final int BLOOD_TYPE_O = 3;

    @Override
    protected int getLayoutRes() {
        return R.layout.fill_phr;
    }

    SweetAlertDialog mDialog;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            mUserInfoBean = intent.getParcelableExtra("user");
            mPHRBean = intent.getParcelableExtra("phr");
            if (mPHRBean != null && mUserInfoBean != null) {
                initValue();
            }
        }

        if (mPHRBean == null) {
            mPHRBean = new PHRBean();
        }
        SpannableString ss = new SpannableString("kg/m2");
        SuperscriptSpan sss = new SuperscriptSpan();
        ss.setSpan(sss, 4, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        mPhrBodyMassIndexUnit.setText(ss);

        mService = RetrofitUtil.getRetrofit().create(EditPHRService.class);
        initToolbar("修改个人PHR", true, null);

    }

    @Override
    protected void initEvents() {
        super.initEvents();

        Observable<CharSequence> o1 = RxTextView.textChanges(mEditTall);
        Observable<CharSequence> o2 = RxTextView.textChanges(mEditWeight);
        Observable.combineLatest(o1, o2, new BiFunction<CharSequence, CharSequence, CharSequence>() {
            @Override
            public CharSequence apply(CharSequence sequence, CharSequence sequence2) throws Exception {
                try {
                    float weight = Float.parseFloat(sequence2.toString());
                    float tall = Float.parseFloat(sequence.toString()) * 1.0f / 100;
                    float result = (float) (weight * 1.0f / Math.pow(tall, 2));
                    DecimalFormat fnum = new DecimalFormat("##0.00");
                    return fnum.format(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "";
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence sequence) throws Exception {
                        mPhrBodyMassIndex.setText(sequence);
                    }
                });


        mCommitOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPHRBean.setBodyMassIndex(mPhrBodyMassIndex.getText().toString());
                mPHRBean.setHeight(mEditTall.getText().toString());
                mPHRBean.setWeight(mEditWeight.getText().toString());
                mPHRBean.setBloodPressureUp(mEditPressureUP.getText().toString());
                mPHRBean.setBloodPressureDown(mEditPressureDown.getText().toString());
                mPHRBean.setHeartRate(mEditHeartRate.getText().toString());
                mPHRBean.setMedicineAllergy(mEditAllergyHistory.getText().toString());
                mPHRBean.setPhysicalExercise(mEditExerciseStatus.getText().toString());
                mDialog = new SweetAlertDialog(EditPHRActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                mDialog.setCancelable(false);
                mDialog.setTitleText("正在提交，请稍后...");
                mDialog.show();
                updatePHR();
            }
        });

        mBloodTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case BLOOD_TYPE_A:
                        mPHRBean.setBloodType("A型");
                        break;
                    case BLOOD_TYPE_B:
                        mPHRBean.setBloodType("B型");
                        break;
                    case BLOOD_TYPE_AB:
                        mPHRBean.setBloodType("AB型");
                        break;
                    case BLOOD_TYPE_O:
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

    private void updatePHR() {
        mService.updatePHR(mPHRBean, Contract.cookie)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(ResponseBody value) {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mDialog.dismiss();
                        mDialog = new SweetAlertDialog(EditPHRActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText(e.getMessage())
                                .setConfirmText("我知道了")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        mDialog.dismiss();
                                    }
                                });
                        mDialog.setCancelable(false);
                        mDialog.show();
                    }

                    @Override
                    public void onComplete() {
                        mDialog.dismiss();
                        mDialog = new SweetAlertDialog(EditPHRActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("提交成功！")
                                .setConfirmText("我知道了")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        mDialog.dismiss();
                                        finish();
                                    }
                                });
                        mDialog.setCancelable(false);
                        mDialog.show();
                    }
                });
    }

    private void initValue() {
        initMap();

        mPhrName.setText(mUserInfoBean.getName());
        mPhrAge.setText(mUserInfoBean.getAge());

        mEditTall.setText(mPHRBean.getHeight());
        mEditWeight.setText(mPHRBean.getWeight());
        mEditHeartRate.setText(mPHRBean.getHeartRate());
        mEditPressureUP.setText(mPHRBean.getBloodPressureUp());
        mEditPressureDown.setText(mPHRBean.getBloodPressureDown());
        mEditExerciseStatus.setText(mPHRBean.getPhysicalExercise());
        mEditAllergyHistory.setText(mPHRBean.getMedicineAllergy());
        mPhrBodyMassIndex.setText(mPHRBean.getBodyMassIndex());

        mBloodTypeSpinner.setSelection(mSpinnerIndex.get(!TextUtils.isEmpty(mPHRBean.getBloodType()) ?
                mPHRBean.getBloodType() : "A型"));
        mDrinkingTypeSpinner.setSelection(mSpinnerIndex.get(!TextUtils.isEmpty(mPHRBean.getDrinkingType()) ?
                mPHRBean.getDrinkingType() : "白酒"));
        mDrinkingFrequencySpinner.setSelection(mSpinnerIndex.get(!TextUtils.isEmpty(mPHRBean.getDrinkingFrequency()) ?
                mPHRBean.getDrinkingFrequency() : "从不"));
        mSmokingAmountSpinner.setSelection(mSpinnerIndex.get(!TextUtils.isEmpty(mPHRBean.getSmokingVolume()) ?
                mPHRBean.getSmokingVolume() : "从不"));
        mAlcoholConsumptionSpinner.setSelection(mSpinnerIndex.get(!TextUtils.isEmpty(mPHRBean.getAlcoholVolume()) ?
                mPHRBean.getAlcoholVolume() : "从不"));
    }

    private void initMap() {
        /**
         * 喝酒频率
         */
        mSpinnerIndex.put("从不", 0);
        mSpinnerIndex.put("偶尔", 1);
        mSpinnerIndex.put("经常", 2);
        mSpinnerIndex.put("总是", 3);

        /**
         * 饮酒类型
         */
        mSpinnerIndex.put("白酒", 0);
        mSpinnerIndex.put("红酒", 1);
        mSpinnerIndex.put("啤酒", 2);
        mSpinnerIndex.put("药酒", 2);

        /**
         * 血型
         */
        mSpinnerIndex.put("A型", 0);
        mSpinnerIndex.put("B型", 1);
        mSpinnerIndex.put("AB型", 2);
        mSpinnerIndex.put("O型", 3);

        /**
         * 吸烟量
         */
        mSpinnerIndex.put("从不", 0);
        mSpinnerIndex.put("少量", 1);
        mSpinnerIndex.put("中等", 2);
        mSpinnerIndex.put("大量", 3);
        mSpinnerIndex.put("上瘾", 4);

        /**
         * 饮酒量
         */
        mSpinnerIndex.put("从不", 0);
        mSpinnerIndex.put("适当", 1);
        mSpinnerIndex.put("中等", 2);
        mSpinnerIndex.put("大量", 3);
    }

    @Override
    protected void onDestroy() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
        super.onDestroy();
    }

    interface EditPHRService {
        @PUT("phr/update")
        Observable<ResponseBody> updatePHR(@Body PHRBean phrBean, @Header("Cookie") String cookie);
    }
}
