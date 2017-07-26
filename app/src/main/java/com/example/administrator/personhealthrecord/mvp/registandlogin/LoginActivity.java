package com.example.administrator.personhealthrecord.mvp.registandlogin;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestcode"+requestCode+"   resultcode"+resultCode);
        switch(requestCode)
            {
                case 1:
                    if(resultCode==0)
                    {
                        String myusername=data.getStringExtra("username");
                        String mypasswrod=data.getStringExtra("password");
                        usrname.setText(myusername);
                        password.setText(mypasswrod);
                    }
                    break;
                default:

                    break;
            }
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        login.setOnClickListener(this);
        regist.setOnClickListener(this);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.logpage;
    }

    @Override
    public IRegistAndLoginPresenter createPresenter() {
        mPresenter=new RegistAndLoginPresenterImpl(this);
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
                            case R.id.regist:
                                regist();
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

    @Override
    public void regist() {
        Log.d(TAG, "regist: ");
        Intent intent=new Intent(LoginActivity.this,RegistActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    public void finishAcitvity() {
        finish();
    }
}
