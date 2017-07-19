package com.example.administrator.personhealthrecord.others;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Administrator on 2017-7-19.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .into(imageView);
    }

    @Override
    public ImageView createImageView(Context context) {
        return null;
    }
}
