package com.example.administrator.personhealthrecord.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.Toast;

/**
 * Created by Administrator on 2017-8-5.
 */

public class DeletableLayout extends LinearLayout {

    View mContentView;
    View mRightMenuView;
    int mRightMenuViewWidth;

    Scroller mScroller;

    public DeletableLayout(Context context) {
        super(context);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext(), new AccelerateInterpolator());
    }

    public DeletableLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DeletableLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        ViewGroup.LayoutParams lp = mContentView.getLayoutParams();
        lp.width = getMeasuredWidth();
        mContentView.setLayoutParams(lp);

        ViewGroup.LayoutParams lp2 = mRightMenuView.getLayoutParams();
        lp2.width = getMeasuredWidth() / 4;
        Log.d("DeletableLayout", "onMeasure" + lp2.width);
        mRightMenuViewWidth = lp2.width;
        lp2.height = mContentView.getMeasuredHeight();
        mRightMenuView.setLayoutParams(lp2);

        int width = mContentView.getMeasuredWidth() + mRightMenuView.getMeasuredWidth() +
                getPaddingLeft() + getPaddingRight();
        int height = Math.max(mContentView.getMeasuredHeight(), mRightMenuView.getMeasuredHeight())
                + getPaddingTop() + getPaddingBottom();
        int widSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        int heightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        setMeasuredDimension(widSpec, heightSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mContentView.layout(0, 0, mContentView.getMeasuredWidth(), getHeight());
        mRightMenuView.layout(mContentView.getMeasuredWidth(), 0,
                mContentView.getMeasuredWidth() + mRightMenuViewWidth, getHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = (int) event.getX();
                if (!mScroller.isFinished()) {
                    return false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) (event.getX() - mLastX);

                scrollBy(-dx, 0);

                mLastX = (int) event.getX();
                break;
            case MotionEvent.ACTION_UP:

                int curX = getScrollX();
                int deltaX;
                if (curX >= mRightMenuViewWidth * 1.0f / 2) {
                    deltaX = mRightMenuViewWidth - curX;
                } else {
                    deltaX = 0 - curX;
                }
                mScroller.startScroll(curX, 0, deltaX, 0, 200);
                invalidate();
                break;
        }
        return true;
    }

    int mLastX;
    int mLastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = (int) (event.getX() - mLastX);
                int deltaY = (int) (event.getY() - mLastY);
                if (deltaX < deltaY) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        mLastX = (int) event.getX();
        mLastY = (int) event.getY();
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void scrollTo(@Px int x, @Px int y) {
        int value = (int) (mRightMenuViewWidth * 1.5f);
        Log.d("DeletableLayout", "x:" + x + "value:" + value);
        if (x > value) {
            x = value;
        } else if (x < -value) {
            x = -value;
        }
        super.scrollTo(x, y);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), 0);
            postInvalidate();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mContentView = getChildAt(0);
        mRightMenuView = getChildAt(1);
        mRightMenuView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Menu Clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
