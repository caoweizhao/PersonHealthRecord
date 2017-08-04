package com.example.administrator.personhealthrecord.util;

import android.animation.Animator;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

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

    public static void createCircularRevealFromTopLeftToRightBottom(View view) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            //创建CircularReveal效果示例
            int width, height;
            width = view.getWidth();
            height = view.getHeight();
            //参数为动画的中心点X,动画的中心点Y，动画开始的半径，动画结束的半径
            Animator animator;
            animator = ViewAnimationUtils.createCircularReveal(view, 0, 0, 0, (float) Math.hypot(
                    width, height));
            animator.setDuration(800);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.start();
        }
    }

    public static void scaleShow(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        view.setVisibility(View.VISIBLE);
        ViewCompat.animate(view)
                .scaleX(1.0f)
                .scaleY(1.0f)
                .alpha(1.0f)
                .setDuration(800)
                .setListener(viewPropertyAnimatorListener)
                .setInterpolator(new LinearInterpolator())
                .start();
    }

    public static void scaleHide(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        ViewCompat.animate(view)
                .scaleX(0.0f)
                .scaleY(0.0f)
                .alpha(0.0f)
                .setDuration(400)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setListener(viewPropertyAnimatorListener)
                .start();
    }

    public static void scaleHide(View view, float scaleFactor) {
        //scaleFactor 0->1
        //scale 1->0.5
        view.setScaleX(1 - 0.5f * scaleFactor);
        view.setScaleY(1 - 0.5f * scaleFactor);
        //view.setAlpha(1 - scaleFactor);
    }

    public static void scaleShow(View view, float scaleFactor) {
        view.setScaleX(scaleFactor);
        view.setScaleY(scaleFactor);
        view.setAlpha(scaleFactor);
    }


}
