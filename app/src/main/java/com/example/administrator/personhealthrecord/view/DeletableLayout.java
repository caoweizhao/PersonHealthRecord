package com.example.administrator.personhealthrecord.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017-8-5.
 */

public class DeletableLayout extends ViewGroup {

    View mContentView;
    View mDeleteView;

    int mWidth;
    int mHeight;

    public DeletableLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mContentView.layout(0, 0, mContentView.getWidth(), mContentView.getHeight());
        mDeleteView.layout(mContentView.getWidth(), 0, mContentView.getWidth() + mDeleteView.getWidth()
                , getHeight());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContentView = getChildAt(0);
        mDeleteView = getChildAt(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        mWidth = mContentView.getMeasuredWidth() + mDeleteView.getMeasuredWidth();
        mHeight = Math.max(mContentView.getMeasuredHeight(), mDeleteView.getMeasuredHeight());
        measure(mWidth, mHeight);
    }

}
