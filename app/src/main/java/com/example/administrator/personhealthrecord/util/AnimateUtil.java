package com.example.administrator.personhealthrecord.util;

import android.animation.Animator;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by Administrator on 2017-7-21.
 */

public class AnimateUtil {

    public static void createCircularReveal(View view) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            //创建CircularReveal效果示例
            int width, height;
            width = view.getWidth();
            height = view.getHeight();
            //参数为动画的中心点X,动画的中心点Y，动画开始的半径，动画结束的半径
            Animator animator = null;
            animator = ViewAnimationUtils.createCircularReveal(view, view.getWidth() / 2, view.getHeight() / 2, 20,
                    Math.max(width, height) / 2);
            animator.setDuration(800);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.start();
        }
    }
}
