package com.example.administrator.personhealthrecord.mvp.log;

import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.util.ToastUitl;


import butterknife.BindView;


public class LoginActivity extends ILoginVIew implements View.OnClickListener{

    @BindView(R.id.login_username)
    EditText usrname;
    @BindView(R.id.login_password)
    EditText password;
    @BindView(R.id.login_login)
    Button login;
    @BindView(R.id.regist)
    Button regist;
    @BindView(R.id.login_regist_by_wechat)
    Button registByWechat;

    @Override
    protected void initEvents() {
        super.initEvents();
        login.setOnClickListener(this);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.logpage;
    }

    @Override
    public ILoginPresenter createPresenter() {
        mPresenter=new LoginPresenterImpl(this);
        return mPresenter;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void onClick(View v) {
                switch (v.getId())
                        {
                            case R.id.login_login:
                                dologin();
                                break;
                            default:
                                break;
                        }
    }

    @Override
    void dologin() {
        if(usrname.getText().toString().equals(""))
            ToastUitl.Toast(getString(R.string.username_cannot_be_empty));
        else  if(password.getText().toString().equals(""))
            ToastUitl.Toast(getString(R.string.password_cannot_empty));
        else
        {
            mPresenter.dologin(usrname.getText().toString(),password.getText().toString());
            Log.d(TAG, "dologin: username"+usrname+"   password"+"");
        }
        Log.d(TAG, "dologin: username"+usrname.getText().toString()+"   password"+password.getText().toString());

    }
}
