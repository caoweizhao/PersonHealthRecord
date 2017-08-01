package com.example.administrator.personhealthrecord.util;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by andy on 2017/8/1.
 */

public class SoftInputFixUtil {
    public static void fix(final View root, final View lastView){
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){
            @Override
                    public void onGlobalLayout(){
                Rect rect=new Rect();
                root.getWindowVisibleDisplayFrame(rect);
                int mainInvisibleHeight=root.getRootView().getHeight()-rect.bottom;
                if(mainInvisibleHeight>200){
                    int[]location=new int[2];
                    lastView.getLocationInWindow(location);
                    int deltaY=location[1]+lastView.getHeight()-rect.bottom;
                    root.scrollBy(0,deltaY);
                }else{
                    root.scrollTo(0,0);
                }
            }
        });
    }
}
