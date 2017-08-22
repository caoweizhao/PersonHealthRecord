package com.example.administrator.personhealthrecord.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.example.administrator.personhealthrecord.R;

/**
 * Created by Administrator on 2017-7-27.
 */

public class WaveImageView extends android.support.v7.widget.AppCompatImageView {
    private static final String TAG = "WaveImageView";
    private int mHeight = 100;
    Path path = new Path();
    Paint mPaint = new Paint();
    //波浪颜色
    private int myColor = Color.WHITE;
    private Xfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
    //是否绘制圆形
    boolean drawCircle = false;
    /**
     * 分数值
     */
    private int value = 0;
    /**
     * 图片高度
     */
    private int imageH;
    /**
     * 图片宽度
     */
    private int imageW;
    /**
     * 初始倍数
     */
    public static final int INIT_TIMES = 30;
    /**
     * 三角函数Y值倍数
     */
    private float TIMES_Y = INIT_TIMES;
    /**
     * 三角函数周期
     */
    private double CIRCLE = 200 * Math.PI;
    /**
     * 偏移量
     */
    private float OFFSET_X = 0;
    Bitmap bitmap;

    Canvas mCanvas;
    private Paint mTextPaint = new Paint();

    public WaveImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        }
        if (mCanvas == null) {
            mCanvas = new Canvas(bitmap);
        }
        mCanvas.drawColor(Color.WHITE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        imageH = h;
        imageW = w;
    }

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

    /**
     * 开始水波动画
     */
    public void startAnimate() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (canAnimate) {
                    try {
                        Thread.sleep(16);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                    OFFSET_X++;
                    /**
                     * 到达三角函数周期值后重置，平滑过渡
                     */
                    if (OFFSET_X == CIRCLE) {
                        OFFSET_X -= CIRCLE;
                    }
                    float fraction = (OFFSET_X % getMeasuredWidth()) / getMeasuredWidth();
                    /**
                     * 高度在0.7-1.2倍之间切换
                     */
                    TIMES_Y = INIT_TIMES * (Math.abs(fraction - 0.5f) + 0.7f);
                    postInvalidate();
                }
            }
        });
        thread.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mHeight = 100;
        if (value != 0) {
            mHeight = (int) (getHeight() * (value * 1.0f / 100));
        }

        /**
         * 绘制水波浪
         **/
        mPaint.setColor(myColor);
        mPaint.setXfermode(null);
        mPaint.setStyle(Paint.Style.FILL);
        path.reset();
        path.moveTo(0, getMeasuredHeight() + 1);
        double y;
        for (double i = 0; i <= getMeasuredWidth(); i++) {
            y = getMeasuredHeight() - (TIMES_Y * Math.sin((i - OFFSET_X) / 100) + mHeight);
            path.lineTo((float) i, (float) y);
        }
        path.lineTo(getMeasuredWidth(), getMeasuredHeight() + 1);
        path.close();
        canvas.drawPath(path, mPaint);

        if (drawCircle) {
            // 创建画笔
            mTextPaint.setColor(0xffffffff);        // 设置颜色
            mTextPaint.setStyle(Paint.Style.FILL);   // 设置样式
            mTextPaint.setTextSize(120);
            Rect targetRect = new Rect((imageW - imageH) / 2, 0, imageH + (imageW - imageH) / 2, imageH);
//            textPaint.measureText("",0,1);
            Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
            int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
            mTextPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(value + "分", targetRect.centerX(), baseline + 50, mTextPaint);
        }
        if (drawCircle) {
            mPaint.setXfermode(mXfermode);
            mCanvas.drawCircle(imageW / 2, imageH / 2, Math.min(imageW, imageH) / 2, mPaint);
            canvas.drawBitmap(bitmap, 0, 0, null);
        }
    }

    /**
     * 设置水波浪的颜色
     */
    public void setColor() {
        myColor = getResources().getColor(R.color.colorPrimary);
    }

    /**
     * 设置分数值
     *
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * 设置是否绘制圆形
     */
    public void drawCircle() {
        drawCircle = true;
    }

    public void setHeightAnimate(int height) {
        ValueAnimator v = ValueAnimator.ofInt(0, height);
        v.setDuration(1000);
        v.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value = (int) animation.getAnimatedValue();
            }
        });
        v.start();
    }
}

