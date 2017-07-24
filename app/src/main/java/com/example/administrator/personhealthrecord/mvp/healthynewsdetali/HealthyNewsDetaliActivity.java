package com.example.administrator.personhealthrecord.mvp.healthynewsdetali;

import android.app.Activity;
import android.os.Bundle;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class HealthyNewsDetaliActivity extends Activity {

    @BindView(R.id.healthy_news_detail_text)
    TextView textView;
    @BindView(R.id.healthy_news_detail_toolbar)
    Toolbar toolbar;
    @BindView(R.id.healthy_news_detali_image)
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy_news_detali);
        ButterKnife.bind(this);
        NewsBean bean=getIntent().getParcelableExtra("NewsBean");
        textView.setText(bean.getContent()+"\n"+"\n"+bean.getdate());
        Glide.with(this)
                .load(bean.getImageUrl())
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
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
