package com.example.administrator.personhealthrecord.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.DiseaseBean;
import com.example.administrator.personhealthrecord.contract.Contract;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Administrator on 2017-8-3.
 */

public class DiseaseDetailActivity extends BaseActivity {

    @BindView(R.id.disease_detail_image)
    ImageView mDiseaseDetailImage;
    @BindView(R.id.summary_view)
    TextView mSummaryView;
    @BindView(R.id.reason_view)
    TextView mReasonView;
    @BindView(R.id.category_view)
    TextView mCategoryView;
    @BindView(R.id.clinicalManifestation_view)
    TextView mClinicalManifestationView;
    @BindView(R.id.diagnosis_view)
    TextView mDiagnosisView;
    @BindView(R.id.treatment_view)
    TextView mTreatmentView;
    @BindView(R.id.prevention_view)
    TextView mPreventionView;

    DiseaseBean mDiseaseBean;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_disease_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sm.setStatusBarTintEnabled(false);
        Intent intent = getIntent();
        if (intent != null) {
            mDiseaseBean = intent.getParcelableExtra("data");
            if (mDiseaseBean == null) {
                SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE);
                dialog.setTitleText("出现未知错误");
                dialog.setCancelable(false);
                dialog.setConfirmText("确定")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismiss();
                                finish();
                            }
                        });
                dialog.show();
            } else {
                initToolbar(mDiseaseBean.getName(), true, null);

                String mImageURL;
                if (mDiseaseBean.getImageUrl().contains("http")) {
                    mImageURL = mDiseaseBean.getImageUrl();
                } else {
                    mImageURL = Contract.DiseaseBase + mDiseaseBean.getImageUrl();
                }
                Glide.with(this)
                        .load(mImageURL)
                        .asBitmap()
                        .centerCrop()
                        .placeholder(R.mipmap.ic_launcher_round)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(mDiseaseDetailImage);
                initValue();
            }
        }
    }

    private void initValue() {
        mSummaryView.setText(mDiseaseBean.getSummary());
        mReasonView.setText(mDiseaseBean.getPathogeny());
        mCategoryView.setText(mDiseaseBean.getCategory());
        mClinicalManifestationView.setText(mDiseaseBean.getClinicalManifestation());
        mDiagnosisView.setText(mDiseaseBean.getDiagnosis());
        mTreatmentView.setText(mDiseaseBean.getTreatment());
        mPreventionView.setText(mDiseaseBean.getPrevention());
    }
}
