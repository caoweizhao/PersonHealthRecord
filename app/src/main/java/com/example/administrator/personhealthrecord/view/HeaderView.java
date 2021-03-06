package com.example.administrator.personhealthrecord.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import com.example.administrator.personhealthrecord.R;
/**
 * 侧滑菜单栏HeaderView
 */
public class HeaderView extends View {

    private Paint mPaint;
    private Path mPath;
    private int mHeight;
    private int mWidth;
    private DisplayMetrics mDisplayMetrics;
    private int mCustomHeight;

    public HeaderView(Context context) {
        super(context);
        init();
    }

    public HeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPath = new Path();
        mDisplayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        mCustomHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24,
                mDisplayMetrics);
    }

    boolean changed = false;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!changed) {
            changed = true;
            mHeight = getMeasuredHeight();
            mWidth = getMeasuredWidth();
            mPath.moveTo(0, mCustomHeight);
            mPath.lineTo(0, mHeight);
            mPath.lineTo(mHeight - mCustomHeight, mHeight);
            mPath.close();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(getResources().getColor(R.color.google_play_yellow));
        canvas.drawRect(0, 0, mWidth, mHeight, mPaint);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //绘制圆弧
            mPaint.setColor(getResources().getColor(R.color.colorPrimary));
            canvas.drawArc(mWidth - mHeight, 0, mWidth + mHeight, 2 * mHeight, -180, 90, true, mPaint);
            //绘制1/4圆
            //int radius = mWidth - (mHeight - mCustomHeight);
            float radius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, mDisplayMetrics);
            mPaint.setColor(getResources().getColor(R.color.google_play_yellow));
            canvas.drawArc(mWidth - radius, mHeight - radius, mWidth + radius, mHeight + radius, -180, 90, true, mPaint);
        }
        //绘制绿色三角形
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(R.color.google_play_green));
        canvas.drawPath(mPath, mPaint);
        //绘制圆
        float cx =  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 87, mDisplayMetrics);
        float cy =  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 41, mDisplayMetrics);
        float cRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) (35 * Math.sqrt(2)), mDisplayMetrics);
        mPaint.setColor(getResources().getColor(R.color.google_play_red));
        canvas.drawCircle(cx, cy, cRadius, mPaint);
    }
}
