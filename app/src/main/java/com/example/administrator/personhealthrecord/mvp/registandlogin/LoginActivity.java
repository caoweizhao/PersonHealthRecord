package com.example.administrator.personhealthrecord.mvp.registandlogin;

import android.content.Intent;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.sharepreference.Acount;
import com.example.administrator.personhealthrecord.util.AnimateUtil;
import com.example.administrator.personhealthrecord.view.WaveImageView;

import butterknife.BindView;


public class LoginActivity extends ILoginVIew implements View.OnClickListener {

    @BindView(R.id.login_activity_parentlayout)
    FrameLayout frameLayout;
    @BindView(R.id.login_username)
    EditText usrname;
    @BindView(R.id.login_password)
    EditText password;
    @BindView(R.id.login_login)
    TextView login;
    @BindView(R.id.regist)
    Button regist;
    @BindView(R.id.login_regist_by_wechat)
    Button registByWechat;

    @BindView(R.id.login_username_input_layout)
    TextInputLayout userNameLayout;
    @BindView(R.id.login_password_input_layout)
    TextInputLayout passwordLayout;

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
        usrname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (userNameLayout.isErrorEnabled()) {
                    userNameLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    password.setText("");
                }
            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (passwordLayout.isErrorEnabled()) {
                    passwordLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {
        acount = new Acount(this);
        usrname.setText(acount.getUser());
        password.setText(acount.getPassword());

        SpannableString spannableString = new SpannableString("SIGN  IN");

        SparseArray<Integer> colors = new SparseArray<>();
        colors.put(0, R.color.google_play_blue);
        colors.put(1, R.color.google_play_yellow);
        colors.put(2, R.color.google_play_red);
        colors.put(3, R.color.google_play_green);
        for (int i = 0; i < spannableString.length(); i++) {
            Log.d("LoginActivity", "initData" + i);
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(colors.get(i % 4)));
            spannableString.setSpan(colorSpan, i, i + 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        RelativeSizeSpan relativeSpan = new RelativeSizeSpan(1.5f);
        RelativeSizeSpan relativeSpan2 = new RelativeSizeSpan(1.3f);
        spannableString.setSpan(relativeSpan, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(relativeSpan2, 6, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        StyleSpan styleSpan = new StyleSpan(Typeface.ITALIC);
        spannableString.setSpan(styleSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        login.setText(spannableString);
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
        AnimateUtil.createCircularReveal(v);
        switch (v.getId()) {
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
        if (TextUtils.isEmpty(usrname.getText())) {
            //usrname.setError(getString(R.string.username_cannot_be_empty));
            userNameLayout.setError(getString(R.string.username_cannot_be_empty));
        } else if (TextUtils.isEmpty(password.getText()))
            passwordLayout.setError(getString(R.string.password_cannot_empty));
        else {
            mPresenter.dologin(usrname.getText().toString(), password.getText().toString());
            Log.d(TAG, "dologin: username" + usrname + "   password" + "");
        }
        Log.d(TAG, "dologin: username" + usrname.getText().toString() + "   password" + password.getText().toString());

    }

    @Override
    public void regist() {
        Log.d(TAG, "regist: ");
        Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void finishAcitvity() {
        finish();
        Log.d(TAG, "dologin: username" + usrname.getText().toString() + "   password" + password.getText().toString());
    }

    @Override
    public void ShowSanck(String string) {
        showMessage(frameLayout,string);
    }

    public void SetAcount(String username,String password)
    {
        acount.setAccount(username,password);
    public void SetAcount(String username, String password) {
        acount.setAccount(username, password);
    }
}
