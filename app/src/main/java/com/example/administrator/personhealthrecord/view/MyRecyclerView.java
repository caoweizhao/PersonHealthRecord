package com.example.administrator.personhealthrecord.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017-8-5.
 */

public class MyRecyclerView extends RecyclerView {

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return e.getAction() != MotionEvent.ACTION_DOWN;
    }
}
