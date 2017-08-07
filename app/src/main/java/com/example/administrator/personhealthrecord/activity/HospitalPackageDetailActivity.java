package com.example.administrator.personhealthrecord.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
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
import com.example.administrator.personhealthrecord.mvp.reserve.ReserveActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andy on 2017/7/27.
 */

public class HospitalPackageDetailActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "HospitalPackageDetaily";
    @BindView(R.id.set_pic)
    ImageView imageView;
    @BindView(R.id.set_name)
    TextView name;
    @BindView(R.id.set_price)
    TextView price;
    @BindView(R.id.set_details)
    TextView detail;

    @BindView(R.id.healthtest_description)
    TextView description;
    @BindView(R.id.setorder_button)
    Button reserve;
    private PackageBean bean;

    @Override
    protected int getLayoutRes() {
        return R.layout.set_detail_layout;
    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        bean = intent.getParcelableExtra("packagebean");
        Log.d(TAG, "onCreate: " + bean.getName());
        Glide.with(this)
                .load(Contract.PackageImageBase + bean.getImageUrl())
                .into(imageView);
        name.setText(bean.getName());
        //检查是否有优惠假如有则显示两个价格
        if (Contract.IS_DISCOUNT)
        {
            SpannableString before=new SpannableString("￥" + bean.getPackagePrice() + " 优惠后：" + "￥" + bean.getFavorablePrice());
            StrikethroughSpan span=new StrikethroughSpan();
            before.setSpan(span,1,(bean.getPackagePrice()+"").length()+1,SpannableString.SPAN_INCLUSIVE_INCLUSIVE);
            price.setText(before);
        }
        else
            price.setText("￥" + bean.getPackagePrice());
        detail.setText(bean.getPackageDetail());
        description.setText(bean.getSummary());
        if (Contract.IS_DISCOUNT)
            initToolbar("优惠套餐", true, null);
        else
            initToolbar("套餐详情", true, null);
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        reserve.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setorder_button:
                Intent intent = new Intent(HospitalPackageDetailActivity.this, ReserveNowActivity.class);
                intent.putExtra("package", bean);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
