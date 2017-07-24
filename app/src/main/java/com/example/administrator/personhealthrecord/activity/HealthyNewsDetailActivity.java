package com.example.administrator.personhealthrecord.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
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
<<<<<<< HEAD
import com.example.administrator.personhealthrecord.contract.Contract;

import java.util.Date;
=======
import com.example.administrator.personhealthrecord.mvp.base.BaseActivity;
>>>>>>> master

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
<<<<<<< HEAD
        setContentView(R.layout.activity_healthy_news_detali);
        ButterKnife.bind(this);
        NewsBean bean=getIntent().getParcelableExtra("NewsBean");
        Date date=new Date(bean.getTime());
        textView.setText(bean.getContent()+"\n"+"\n"+bean.getdate());
        Glide.with(this)
                .load(Contract.ImageUrl+bean.getImageUrl())
=======


    }

    @Override
    protected void initData() {
        mNewsBean = getIntent().getParcelableExtra("NewsBean");
        textView.setText(mNewsBean.getContent() + "\n" + "\n" + mNewsBean.getTime());
        mTitleTextView.setText(mNewsBean.getTitle());
        Glide.with(this)
                .load(mNewsBean.getImageUrl())
>>>>>>> master
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
