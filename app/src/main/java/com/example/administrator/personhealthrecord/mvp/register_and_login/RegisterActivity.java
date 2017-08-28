package com.example.administrator.personhealthrecord.mvp.register_and_login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.util.ToastUtil;

import butterknife.BindView;

public class RegisterActivity extends ILoginVIew implements View.OnClickListener {

    @BindView(R.id.regist_layout_parent)
    LinearLayout mParentLayout;
    @BindView(R.id.regist_username)
    EditText mUsername;
    @BindView(R.id.regist_password)
    EditText mPassword;
    @BindView(R.id.regist_phone_number)
    EditText mPhoneNumber;
    @BindView(R.id.regist_vertify_code)
    EditText mVerifyCode;
    @BindView(R.id.regist_get_vertify_code)
    Button getVerifyCode;
    @BindView(R.id.regist_regsiter)
    Button mRegisterButton;

    @Override
    protected int getLayoutRes() {
        return R.layout.regpage;
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        mRegisterButton.setOnClickListener(this);
        getVerifyCode.setOnClickListener(this);
    }

    @Override
    public IRegisterAndLoginPresenter createPresenter() {
        return new RegisterAndLoginPresenterImpl(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    void doLogin() {

    }

    @Override
    public void register() {
        if (mUsername.getText().toString().equals(""))
            ToastUtil.Toast(getString(R.string.username_cannot_be_empty));
        else if (mPassword.getText().toString().equals(""))
            ToastUtil.Toast(getString(R.string.password_cannot_empty));
        else
            mPresenter.register(mUsername.getText().toString(), mPassword.getText().toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regist_regsiter:
                register();
                break;
            case R.id.regist_get_vertify_code:
                break;
            default:
                break;
        }
    }

    @Override
    public void finishActivity() {
        Intent intent = new Intent();
        intent.putExtra("mUsername", mUsername.getText().toString());
        intent.putExtra("mPassword", mPassword.getText().toString());
        setResult(6, intent);
        finish();
    }

    @Override
    public void showSnackBar(String string) {
        showMessage(mParentLayout, string);
    }
}
