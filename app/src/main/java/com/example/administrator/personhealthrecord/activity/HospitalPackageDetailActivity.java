package com.example.administrator.personhealthrecord.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.PackageBean;
import com.example.administrator.personhealthrecord.contract.Contract;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andy on 2017/7/27.
 */

public class HospitalPackageDetailActivity extends AppCompatActivity{
    private static final String TAG="HospitalPackageDetaily";
    @BindView(R.id.set_pic)
    ImageView imageView;
    @BindView(R.id.set_name)
    TextView name;
    @BindView(R.id.set_price)
    TextView price;
    @BindView(R.id.set_details)
    TextView detail;
    @BindView(R.id.appointment_count)
    TextView count;
    @BindView(R.id.healthtest_description)
    TextView description;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_detail_layout);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        PackageBean bean=intent.getParcelableExtra("packagebean");
        Log.d(TAG, "onCreate: "+bean.getName());
        Glide.with(this)
                .load(Contract.PackageImageBase+bean.getImageUrl())
                .into(imageView);
        name.setText(bean.getName());
        price.setText("￥"+bean.getPackagePrice());
        detail.setText(bean.getPackageDetail());
        description.setText(bean.getSummary());
        count.setText(bean.getAllocatedQuantity()+"");

    }
}
