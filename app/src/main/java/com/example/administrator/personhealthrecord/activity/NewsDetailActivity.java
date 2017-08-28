package com.example.administrator.personhealthrecord.activity;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.NewsBean;
import com.example.administrator.personhealthrecord.contract.Contract;

import butterknife.BindView;


public class NewsDetailActivity extends BaseActivity {

    @BindView(R.id.news_detail_text)
    public TextView mNewsDetailText;
    @BindView(R.id.toolbar)
    public Toolbar mToolbar;
    @BindView(R.id.news_detail_image)
    public ImageView mDetailImage;
    @BindView(R.id.news_text_title)
    TextView mTitleTextView;
    private NewsBean mNewsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sm.setStatusBarTintEnabled(false);
    }

    @Override
    protected void initData() {
        initToolbar("新闻详情", true, null);
        mNewsBean = getIntent().getParcelableExtra("NewsBean");
        mNewsDetailText.setText(mNewsBean.getContent() + "\n" + "\n" + mNewsBean.getDate());
        mTitleTextView.setText(mNewsBean.getTitle());
        String mImageUrl = mNewsBean.getImageUrl();
        if (!mNewsBean.getImageUrl().contains("http")) {
            mImageUrl = Contract.ImageUrl + mNewsBean.getImageUrl();
        }

        Glide.with(this)
                .load(mImageUrl)
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
                .into(mDetailImage);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_news_detali;
    }
}
