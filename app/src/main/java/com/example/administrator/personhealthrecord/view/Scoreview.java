package com.example.administrator.personhealthrecord.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;

/**
 * Created by andy on 2017/7/21.
 */

public class Scoreview extends android.view.View{
    private Paint paint = new Paint();
    private static final String TAG="ScoreView";
    private float percent=90;
    private float nowPercent=0;
    private Handler handler;
    private int height;
    private int widht;
    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
    }
    public Scoreview(Context context, int percent) {
        super(context);
        this.percent=percent;

    }

    public Scoreview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Scoreview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(widht>height)
            widht=height;
        else
            height=widht;
        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(5);
//        canvas.drawCircle(widht/2,widht/2,(widht/2), paint);
//        canvas.drawCircle(widht/2,widht/2,(widht/2)-60, paint);
        paint.setColor(0xff05d09b);
        paint.setStrokeWidth(100);
        canvas.drawArc(50,50,widht-50,widht-50,-90,nowPercent*(float)3.6,false,paint);
        Log.d(TAG, "onDraw: "+nowPercent);
        Paint textPaint = new Paint();          // 创建画笔
        textPaint.setColor(0xff4f4dbc);        // 设置颜色
        textPaint.setStyle(Paint.Style.FILL);   // 设置样式
        textPaint.setTextSize(200);
        Rect targetRect = new Rect(0, 0, widht, widht);
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        textPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText((int)percent+"", targetRect.centerX(), baseline+50, textPaint);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.height=h;
        this.widht=w;
    }

    public void startAction(float pecent)
    {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100;i++)
                {
                    nowPercent=(percent/100)*(i+1);
                    Message m=handler.obtainMessage();
                    m.what=0;
                    handler.sendMessage(m);
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "run: "+nowPercent);
                }
            }
        });
        this.percent=pecent;
        handler=new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch(msg.what)
                {
                    case 0:
                        invalidate();
                        break;
                    default:

                        break;
                }
            }
        };
        thread.start();
    }
}
