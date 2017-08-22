package com.example.administrator.personhealthrecord.sharepreference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by andy on 2017/7/30.
 */

public class Account {
    private static final String FILE_NAME = "account";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_USER = "user";
    private SharedPreferences mPreferences;

    public Account(Context context) {
        mPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public void setAccount(String user, String password) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(KEY_USER, user);
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public String getUser() {
        return mPreferences.getString(KEY_USER, "");
    }

    public String getPassword() {
        return mPreferences.getString(KEY_PASSWORD, "");
    }
}
