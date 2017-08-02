package com.example.administrator.personhealthrecord.activity;

import android.graphics.Outline;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2017-7-26.
 */

public class TestActivity extends BaseActivity {

    @BindView(R.id.imageView)
    ImageView mImageView;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_test_layout;
    }

    @Override
    protected void initData() {
        Glide.with(this)
                .load(R.drawable.news_bg)
                .centerCrop()
                .into(mImageView);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ViewOutlineProvider outlineProvider = new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        outline.setOval(0, 0, view.getWidth(), view.getHeight());
                    }
                }
            };
            mImageView.setClipToOutline(true);
            mImageView.setOutlineProvider(outlineProvider);
        }
    }
}