package com.example.administrator.personhealthrecord.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017-7-27.
 */

public class WaveImageView extends android.support.v7.widget.AppCompatImageView {

    Path path = new Path();
    Paint mPaint = new Paint();
    /**
     * 初始倍数
     */
    public static final int INIT_TIMES = 30;
    private float TIMES_Y = INIT_TIMES;
    /**
     * 三角函数周期
     */
    private double CIRCLE = 200 * Math.PI;
    /**
     * 偏移量
     */
    private float OFFSET_X = 0;

    public WaveImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    ReentrantLock lock = new ReentrantLock();
    volatile boolean canAnimate = true;

    Thread thread;

    public void stopAnim() {
        canAnimate = false;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnim();
    }

    public void startAnimate() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (canAnimate) {
                    lock.lock();
                    try {
                        Thread.sleep(16);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                    OFFSET_X++;
                    if (OFFSET_X == CIRCLE) {
                        OFFSET_X -= CIRCLE;
                    }
                    float fraction = (OFFSET_X % getMeasuredWidth()) / getMeasuredWidth();
                    TIMES_Y = INIT_TIMES * (Math.abs(fraction - 0.5f) + 0.7f);
                    postInvalidate();
                    lock.unlock();
                }
            }
        });
        thread.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        path.reset();
        path.moveTo(0, getMeasuredHeight() + 1);
        double y;
        for (double i = 0; i <= getMeasuredWidth(); i++) {
            y = getMeasuredHeight() - (TIMES_Y * Math.sin((i - OFFSET_X) / 100) + 100);
            path.lineTo((float) i, (float) y);
        }
        path.lineTo(getMeasuredWidth(), getMeasuredHeight() + 1);
        path.close();
        canvas.drawPath(path, mPaint);
    }


}
