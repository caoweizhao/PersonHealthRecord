package com.example.administrator.personhealthrecord.activity;


import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.NewsBean;
import com.example.administrator.personhealthrecord.contract.Contract;

import butterknife.BindView;


public class HealthyNewsDetailActivity extends BaseActivity {
    @BindView(R.id.healthy_news_detail_text)
    public TextView mHealthyNewsDetailTextView;
    @BindView(R.id.healthy_news_detail_toolbar)
    public Toolbar mToolbar;
    @BindView(R.id.healthy_news_detali_image)
    public ImageView mHealthyNewsDetailImageView;
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
        mHealthyNewsDetailTextView.setText(mNewsBean.getContent() + "\n" + "\n" + mNewsBean.getDate());
        mTitleTextView.setText(mNewsBean.getTitle());
        String imageUrl;
        if (mNewsBean.getImageUrl().contains("http"))
            imageUrl = mNewsBean.getImageUrl();
        else
            imageUrl = Contract.HealthyNewsImageUrl + mNewsBean.getImageUrl();
        Glide.with(this)
                .load(imageUrl)
                .asBitmap()
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mHealthyNewsDetailImageView);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void initEvents() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
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
