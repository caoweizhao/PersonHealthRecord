package com.example.administrator.personhealthrecord.util;

import android.app.Activity;
import android.content.Intent;

import com.example.administrator.personhealthrecord.mvp.register_and_login.LoginActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Administrator on 2017-8-5.
 */

public class DialogUtil {

    public static SweetAlertDialog getLoginDialog(final Activity activity) {
        final SweetAlertDialog mDialog = new SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE);
        mDialog.setTitleText("登录提醒");
        mDialog.setContentText("当前操作需要您登录，是否前往登录？");
        mDialog.setCancelText("不了");
        mDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                mDialog.dismiss();
                activity.finish();
            }
        });
        mDialog.setConfirmText("好的");
        mDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                mDialog.dismiss();
                Intent intent = new Intent(activity, LoginActivity.class);
                activity.startActivity(intent);
            }
        });
        mDialog.setCancelable(false);
        return mDialog;
    }

    public static SweetAlertDialog getLoadingDialog(final Activity activity) {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(activity, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("正在处理...");
        sweetAlertDialog.setCancelable(false);
        return sweetAlertDialog;
    }

    public static SweetAlertDialog getSuccessDialog(final Activity activity, String msg,
                                                    final boolean confirmToQuit) {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE);
        sweetAlertDialog.setTitleText(msg);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setConfirmText("好的")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        if (confirmToQuit) {
                            activity.finish();
                        }
                    }
                });
        return sweetAlertDialog;
    }
}
