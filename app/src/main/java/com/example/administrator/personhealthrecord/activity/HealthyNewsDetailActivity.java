package com.example.administrator.personhealthrecord.activity;


import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.NewsBean;



import java.util.Date;

import com.example.administrator.personhealthrecord.mvp.base.BaseActivity;


import butterknife.BindView;


public class HealthyNewsDetailActivity extends BaseActivity {

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
    }

    @Override
    protected void initData() {

        mNewsBean=getIntent().getParcelableExtra("NewsBean");
        Date date=new Date(mNewsBean.getTime());
        Log.d(TAG, "initData: "+mNewsBean.getTitle());
        textView.setText(mNewsBean.getContent()+"\n"+"\n"+mNewsBean.getdate());
        mTitleTextView.setText(mNewsBean.getTitle());
        Glide.with(this)
                .load(mNewsBean.getImageUrl())

                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .fitCenter()
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
