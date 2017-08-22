package com.example.administrator.personhealthrecord.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.v4.view.NestedScrollingChild;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by Administrator on 2017-8-5.
 *
 */

public class DeletableLayout extends LinearLayout implements NestedScrollingChild {

    private int mDeltaX;
    private int mDeltaY;

    public interface OnDeleteMenuClickListener {
        void onDeleteMenuClick();
    }

    public interface onContentClickListener {
        void onContentClick();
    }

    onContentClickListener mOnClickListener;

    public void setContentClickListener(onContentClickListener listener) {
        mOnClickListener = listener;
    }

    OnDeleteMenuClickListener mListener;

    View mContentView;
    View mRightMenuView;
    int mRightMenuViewWidth;
    Scroller mScroller;

    public DeletableLayout(Context context) {
        super(context);
        init();
    }

    public void setDeleteMenuListener(OnDeleteMenuClickListener listener) {
        this.mListener = listener;
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
        mRightMenuViewWidth = lp2.width;
        lp2.height = mContentView.getMeasuredHeight();
        mRightMenuView.setLayoutParams(lp2);

        int width = mContentView.getMeasuredWidth() + mRightMenuViewWidth +
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
                mDownTime = System.currentTimeMillis();
                ddx = event.getX();
                ddy = event.getY();
                if (!mScroller.isFinished()) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) (event.getX() - mLastX);
                scrollBy(-dx, 0);
                break;
            case MotionEvent.ACTION_UP:

                float deltaX = Math.abs(ddx - event.getX());
                float deltaY = Math.abs(ddy - event.getY());
                mUpTime = System.currentTimeMillis();
                if (mUpTime - mDownTime < 100 && deltaX < 10 && deltaY < 10) {
                    if (mOnClickListener != null) {
                        mOnClickListener.onContentClick();
                    }
                }

                int curX = getScrollX();
                int deltaX2;
                if (curX >= mRightMenuViewWidth * 1.0f / 2) {
                    deltaX2 = mRightMenuViewWidth - curX;
                } else {
                    deltaX2 = 0 - curX;
                }
                mScroller.startScroll(curX, 0, deltaX2, 0, 200);
                invalidate();
                break;
        }
        mLastX = (int) event.getX();
        return true;
    }

    int mLastX;
    int x;
    int y;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    long mDownTime;
    long mUpTime;
    float ddx = 0;
    float ddy = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);

                break;
            case MotionEvent.ACTION_MOVE:
                mDeltaX = Math.abs((int) (event.getX() - x));
                mDeltaY = Math.abs((int) (event.getY() - y));
                if (mDeltaX < mDeltaY) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                    int c = getScrollX();
                    int dx;
                    if (c >= mRightMenuViewWidth * 1.0f / 2) {
                        dx = mRightMenuViewWidth - c;
                    } else {
                        dx = 0 - c;
                    }
                    mScroller.startScroll(c, 0, dx, 0, 200);
                    invalidate();
                } else {
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }

        x = (int) event.getX();
        y = (int) event.getY();
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void scrollTo(@Px int x, @Px int y) {
        int value = (int) (mRightMenuViewWidth * 1.5f);
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
                if (mListener != null) {
                    mListener.onDeleteMenuClick();
                }
            }
        });
    }

    public void closeDeleteMenu() {
        scrollTo(0, 0);
    }
}
