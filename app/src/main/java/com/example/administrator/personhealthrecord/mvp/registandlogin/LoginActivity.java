package com.example.administrator.personhealthrecord.mvp.registandlogin;

import android.content.Intent;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.sharepreference.Acount;
import com.example.administrator.personhealthrecord.util.AnimateUtil;
import com.example.administrator.personhealthrecord.util.ToastUitl;
import com.example.administrator.personhealthrecord.view.WaveImageView;


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

    private Acount acount;
    private float width;
    private float height;
    private DisplayMetrics mMetrics;
    private FloatingActionButton mFab;
    @BindView(R.id.login_container)
    LinearLayout mContainer;

    @Override
    protected void initEvents() {
        super.initEvents();
        login.setOnClickListener(this);
        regist.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        acount=new Acount(this);
        usrname.setText(acount.getUser());
        password.setText(acount.getPassword());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sm.setStatusBarTintEnabled(false);
        WaveImageView myImageView = (WaveImageView) findViewById(R.id.login_image_view);
        /*Glide.with(this)
                .load(R.drawable.login_bg)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(myImageView);*/
        myImageView.startAnimate();

        mMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
        width = mMetrics.widthPixels;
        height = mMetrics.heightPixels;
        startAnimate();
    }

    private void startAnimate() {
        mFab = (FloatingActionButton) findViewById(R.id.login_logo);
        ObjectAnimator animator = ObjectAnimator.ofFloat(mFab, "translationY", 0,
                height * 8 / 10, height * 4 / 10, height * 6 / 10, height / 2);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(3000);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mFab, "translationY", height / 2,
                        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, mMetrics));
                objectAnimator.setDuration(1500);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Animator animator = ViewAnimationUtils.createCircularReveal(mContainer, 0, 0, 0,
                            (float) Math.hypot(mContainer.getWidth(), mContainer.getHeight()));
                    animator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            mContainer.setVisibility(View.VISIBLE);
                        }
                    });
                    animator.setDuration(1500);
                    AnimatorSet set = new AnimatorSet();
                    set.playTogether(objectAnimator, animator);
                    set.start();
                } else {
                    objectAnimator.start();
                    AnimateUtil.scaleShow(mContainer, null);
                }
            }
        });
        animator.start();
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
        Log.d(TAG, "dologin: username" + usrname.getText().toString() + "   password" + password.getText().toString());
    }

    public void SetAcount(String username,String password)
    {
        acount.setAccount(username,password);
    }
}
