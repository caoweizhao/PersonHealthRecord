package com.example.administrator.personhealthrecord.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

import com.example.administrator.personhealthrecord.R;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017-7-27.
 */

public class WaveImageView extends android.support.v7.widget.AppCompatImageView {
    private static final String TAG="WaveImageView";
    private int myHeight=100;
    Path path = new Path();
    Paint mPaint = new Paint();
    private int myColor=Color.WHITE;
    private Context context;
    private int value = 0;
    /**
     * 初始倍数
     */
    private int imageH;
    private int imageW;
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
    Bitmap bitmap;

    Canvas mCanvas;

    public WaveImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

         bitmap = Bitmap.createBitmap(getMeasuredWidth(),getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(bitmap);
        mCanvas.drawColor(Color.WHITE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        imageH=h;
        imageW=w;
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
        myHeight = 100;
        if(value!=0){
            myHeight=(int)(getHeight()*(value*1.0f/100));
        }

        mPaint.setColor(myColor);
        mPaint.setXfermode(null);
        mPaint.setStyle(Paint.Style.FILL);
        path.reset();
        path.moveTo(0, getMeasuredHeight() + 1);
        double y;
        for (double i = 0; i <= getMeasuredWidth(); i++) {
            y = getMeasuredHeight() - (TIMES_Y * Math.sin((i - OFFSET_X) / 100) + myHeight);
            path.lineTo((float) i, (float) y);
        }
        path.lineTo(getMeasuredWidth(), getMeasuredHeight() + 1);
        path.close();
        canvas.drawPath(path, mPaint);

        if(drawCircle)
        {

            Paint textPaint = new Paint();          // 创建画笔
            textPaint.setColor(0xffffffff);        // 设置颜色
            textPaint.setStyle(Paint.Style.FILL);   // 设置样式
            textPaint.setTextSize(120);
            Rect targetRect = new Rect((imageW-imageH)/2, 0,imageH+(imageW-imageH)/2, imageH);
//            textPaint.measureText("",0,1);
            Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
            int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
            textPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(value+"分", targetRect.centerX(), baseline+50, textPaint);
        }
        if (drawCircle ) {
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            mCanvas.drawCircle(imageW/2,imageH/2,Math.min(imageW,imageH)/2,mPaint);
            canvas.drawBitmap(bitmap,0,0,null);
        }

    }
   public void   setColor()
   {
       myColor=getResources().getColor(R.color.colorPrimary);
   }
    public void setHight(int value)
    {
        this.value = value;
         invalidate();
    }

    boolean drawCircle = false;
    public void drawCircle(){
        drawCircle = true;
    }

}

