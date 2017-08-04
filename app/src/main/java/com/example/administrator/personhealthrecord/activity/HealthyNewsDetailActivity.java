package com.example.administrator.personhealthrecord.activity;


import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.NewsBean;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.base.BaseActivity;

import java.util.Date;

import butterknife.BindView;


public class HealthyNewsDetailActivity extends BaseActivity {
    private String mImageUrl;
    @BindView(R.id.healthy_news_detail_text)
    public TextView textView;
    @BindView(R.id.healthy_news_detail_toolbar)
    public Toolbar toolbar;
    @BindView(R.id.healthy_news_detali_image)
    public ImageView imageView;
    @BindView(R.id.health_news_text_title)
    TextView mTitleTextView;
    private NewsBean mNewsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sm.setStatusBarTintEnabled(false);
    }

    @Override
    protected void initData() {
        mNewsBean = getIntent().getParcelableExtra("NewsBean");
        Date date = new Date(mNewsBean.getTime());
        textView.setText(mNewsBean.getContent() + "\n" + "\n" + mNewsBean.getdate());
        mTitleTextView.setText(mNewsBean.getTitle());
        if(mNewsBean.getImageUrl().contains("http"))
            mImageUrl=mNewsBean.getImageUrl();
        else
        mImageUrl=Contract.HealthyNewsImageUrl + mNewsBean.getImageUrl();
        Log.d("aaa", "initData" + mImageUrl);
        Glide.with(this)
                .load(mImageUrl)
                .asBitmap()
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
    }

    @Override
    protected void initEvents() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_healthy_news_detali;
    }

}
