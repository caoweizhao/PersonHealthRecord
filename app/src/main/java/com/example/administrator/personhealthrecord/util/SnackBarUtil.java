package com.example.administrator.personhealthrecord.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by andy on 2017/7/30.
 */

public class SnackBarUtil {
    public static void ShowSnackBar(View layot,String message,String Confirm)
    {
        Snackbar.make(layot,message, Snackbar.LENGTH_LONG).setAction(Confirm, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }
    public static void ShowSnackBarWithListner(View view, String message, String Confirm,
                                               final SnackBarOnClickListener listener)
    {
        Snackbar.make(view,message, Snackbar.LENGTH_LONG).setAction(Confirm, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnConfirm();
            }
        }).show();
    }
    interface SnackBarOnClickListener
    {
        void OnConfirm();
    }
}
