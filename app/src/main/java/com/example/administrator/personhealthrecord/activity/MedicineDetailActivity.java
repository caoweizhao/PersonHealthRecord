package com.example.administrator.personhealthrecord.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.MedicineBean;
import com.example.administrator.personhealthrecord.contract.Contract;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Administrator on 2017-8-2.
 */

public class MedicineDetailActivity extends BaseActivity {

    @BindView(R.id.medicine_detail_image)
    ImageView mImageView;
    @BindView(R.id.medicine_name)
    TextView mMedicineName;
    @BindView(R.id.medicine_code)
    TextView mMedicineCode;
    @BindView(R.id.medicine_category)
    TextView mMedicineCategory;
    @BindView(R.id.medicine_character)
    TextView mMedicineCharacter;
    @BindView(R.id.medicine_component)
    TextView mMedicineComponent;
    @BindView(R.id.medicine_function)
    TextView mMedicineFunction;
    @BindView(R.id.medicine_method)
    TextView mMedicineMethod;
    @BindView(R.id.medicine_attention)
    TextView mMedicineAttention;
    @BindView(R.id.medicine_standard)
    TextView mMedicineStandard;
    @BindView(R.id.medicine_expiry_date)
    TextView mMedicineExpiryDate;
    @BindView(R.id.medicine_use_taboo)
    TextView mMedicineUseTaboo;
    private MedicineBean mMedicineBean;
    private SweetAlertDialog mDialog;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_medicine_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sm.setStatusBarTintEnabled(false);
        initToolbar("药品详情", true, null);
        Intent intent = getIntent();
        if (intent != null) {
            mMedicineBean = intent.getParcelableExtra("data");
            if (mMedicineBean != null) {
                initValue();

                String mImageURL;
                if (mMedicineBean.getImageUrl().contains("http")) {
                    mImageURL = mMedicineBean.getImageUrl();
                } else {
                    mImageURL = Contract.MedicalBase + mMedicineBean.getImageUrl();
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Glide.with(this)
                            .load(mImageURL)
                            .asBitmap()
                            .centerCrop()
                            .placeholder(R.mipmap.ic_launcher_round)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(mImageView);
                }
            } else {
                mDialog = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE);
                mDialog.setCancelable(false);
                mDialog.setConfirmText("我知道了")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                mDialog.dismiss();
                                finish();
                            }
                        });
                mDialog.setTitleText("出现未知错误！");
                mDialog.show();
            }
        }
    }

    private void initValue() {
        mMedicineName.setText(mMedicineBean.getName());
        mMedicineCode.setText(mMedicineBean.getCode());
        mMedicineCategory.setText(mMedicineBean.getCategory());
        mMedicineCharacter.setText(mMedicineBean.getCharacter());
        mMedicineComponent.setText(mMedicineBean.getComponent());
        mMedicineFunction.setText(mMedicineBean.getFunction());
        mMedicineMethod.setText(mMedicineBean.getMethod());
        mMedicineAttention.setText(mMedicineBean.getAttention());
        mMedicineExpiryDate.setText(mMedicineBean.getExpiryDate());
        mMedicineStandard.setText(mMedicineBean.getStandard());
        mMedicineUseTaboo.setText(mMedicineBean.getUseTaboo());
    }
}
