package com.example.administrator.personhealthrecord.sharepreference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by andy on 2017/7/30.
 */

public class Acount {
    private static final String FILE_NAME = "account";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_USER="user";
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    public Acount(Context context) {
        mPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }
    public void setAccount(String user,String password){
        mEditor.putString(KEY_USER,user);
        mEditor.putString(KEY_PASSWORD,password);
        mEditor.commit();
    }
    public String getUser(){
        return mPreferences.getString(KEY_USER,"");
    }
    public String getPassword(){
        return mPreferences.getString(KEY_PASSWORD,"");
    }
}
