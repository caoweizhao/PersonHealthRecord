package com.example.administrator.personhealthrecord.activity;

import android.content.Intent;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.PackageBean;
import com.example.administrator.personhealthrecord.contract.Contract;

import butterknife.BindView;

/**
 * Created by andy on 2017/7/27.
 */

public class HospitalPackageDetailActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "HospitalPackageDetaily";
    @BindView(R.id.set_pic)
    ImageView mImageView;
    @BindView(R.id.set_name)
    TextView mName;
    @BindView(R.id.set_price)
    TextView mPrice;
    @BindView(R.id.set_details)
    TextView mDetail;
    @BindView(R.id.healthtest_description)
    TextView mDescription;
    @BindView(R.id.setorder_button)
    Button mReserve;
    private PackageBean mPackageBean;

    @Override
    protected int getLayoutRes() {
        return R.layout.set_detail_layout;
    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        mPackageBean = intent.getParcelableExtra("packagebean");
        Log.d(TAG, "onCreate: " + mPackageBean.getName());
        Glide.with(this)
                .load(Contract.PackageImageBase + mPackageBean.getImageUrl())
                .into(mImageView);
        mName.setText(mPackageBean.getName());
        //检查是否有优惠假如有则显示两个价格
        if (Contract.IS_DISCOUNT) {
            SpannableString before = new SpannableString("￥" + mPackageBean.getPackagePrice() + " 优惠后：" + "￥" + mPackageBean.getFavorablePrice());
            StrikethroughSpan span = new StrikethroughSpan();
            before.setSpan(span, 1, (mPackageBean.getPackagePrice() + "").length() + 1, SpannableString.SPAN_INCLUSIVE_INCLUSIVE);
            mPrice.setText(before);
        } else
            mPrice.setText("￥" + mPackageBean.getPackagePrice());
        mDetail.setText(mPackageBean.getPackageDetail());
        mDescription.setText(mPackageBean.getSummary());
        if (Contract.IS_DISCOUNT)
            initToolbar("优惠套餐", true, null);
        else
            initToolbar("套餐详情", true, null);
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        mReserve.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setorder_button:
                Intent intent = new Intent(HospitalPackageDetailActivity.this, ReserveNowActivity.class);
                intent.putExtra("package", mPackageBean);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
