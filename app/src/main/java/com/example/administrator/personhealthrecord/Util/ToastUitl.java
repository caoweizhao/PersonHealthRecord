package com.example.administrator.personhealthrecord.Util;

import android.widget.Toast;

import com.example.administrator.personhealthrecord.application.MyApplication;

/**
 * Created by andy on 2017/7/18.
 */

public class ToastUitl {
    public static void Toast(String msg)
    {
        Toast.makeText(MyApplication.getContext(),msg,Toast.LENGTH_LONG).show();
    }
}
