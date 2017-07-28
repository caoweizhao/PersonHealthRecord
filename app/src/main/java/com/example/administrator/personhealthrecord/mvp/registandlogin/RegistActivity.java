package com.example.administrator.personhealthrecord.mvp.registandlogin;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.util.ToastUitl;

import butterknife.BindView;

public class RegistActivity extends ILoginVIew implements View.OnClickListener{


    @BindView(R.id.regist_username)
    EditText username;
    @BindView(R.id.regist_password)
    EditText password;
    @BindView(R.id.regist_phone_number)
    EditText phoneNumber;
    @BindView(R.id.regist_vertify_code)
    EditText vertifyCode;
    @BindView(R.id.regist_get_vertify_code)
    Button getVertifyCode;
    @BindView(R.id.regist_regsiter)
    Button regist;
    @Override
    protected int getLayoutRes() {
        return R.layout.regpage;
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        regist.setOnClickListener(this);
        getVertifyCode.setOnClickListener(this);
    }

    @Override
    public IRegistAndLoginPresenter createPresenter() {
        return new RegistAndLoginPresenterImpl(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    void dologin() {

    }

    @Override
    public void regist() {
        if(username.getText().toString().equals(""))
            ToastUitl.Toast(getString(R.string.username_cannot_be_empty));
        else if(password.getText().toString().equals(""))
            ToastUitl.Toast(getString(R.string.password_cannot_empty));
        else
            mPresenter.regist(username.getText().toString(),password.getText().toString());
    }

    @Override
    public void onClick(View v) {
                switch (v.getId())
                        {
                            case R.id.regist_regsiter:
                                regist();
                                break;
                            case R.id.regist_get_vertify_code:
                                //finishAcitvity();
                                break;
                            default:
                                break;
                        }
    }

    @Override
    public void finishAcitvity() {
        Intent intent=new Intent();
        intent.putExtra("username",username.getText().toString());
        intent.putExtra("password",password.getText().toString());
        setResult(6,intent);
        finish();
    }
}
