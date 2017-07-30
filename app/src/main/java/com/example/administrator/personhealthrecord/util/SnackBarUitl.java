package com.example.administrator.personhealthrecord.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by andy on 2017/7/30.
 */

public class SnackBarUitl {
    public static void ShowSnackBar(View layot,String message,String Confirm)
    {
        Snackbar.make(layot,message, Snackbar.LENGTH_LONG).setAction(Confirm, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }
    public static void ShowSnackBarWithListner(View layot, String message, String Confirm, final SncakBarOnClickListner listner)
    {
        Snackbar.make(layot,message, Snackbar.LENGTH_LONG).setAction(Confirm, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.OnComfirm();
            }
        }).show();
    }
    interface SncakBarOnClickListner
    {
        void OnComfirm();
    }
}
