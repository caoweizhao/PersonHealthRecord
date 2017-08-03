package com.example.administrator.personhealthrecord.mvp.registandlogin;

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
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.application.MyApplication;
import com.example.administrator.personhealthrecord.sharepreference.Acount;
import com.example.administrator.personhealthrecord.util.AnimateUtil;
import com.example.administrator.personhealthrecord.view.WaveImageView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class LoginActivity extends ILoginVIew implements View.OnClickListener {

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

        Observable<CharSequence> userNameObservable = RxTextView.textChanges(usrname).share();
        userNameObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence sequence) throws Exception {
                        if (TextUtils.isEmpty(sequence)) {
                            password.setText("");
                        }
                    }
                });
        Observable<CharSequence> passwordObservable = RxTextView.textChanges(password);

        Observable.combineLatest(userNameObservable, passwordObservable, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence usrName, CharSequence psw) throws Exception {
                if (!TextUtils.isEmpty(usrName) || TextUtils.isEmpty(password.getText())) {
                    if (userNameLayout.isErrorEnabled()) {
                        userNameLayout.setErrorEnabled(false);
                    }
                }

                if (!TextUtils.isEmpty(password.getText()) && TextUtils.isEmpty(usrname.getText())) {
                    userNameLayout.setError(getString(R.string.username_cannot_be_empty));
                }

                return !TextUtils.isEmpty(usrName) && !TextUtils.isEmpty(psw);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        login.setEnabled(aBoolean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("LoginActivity", "accept" + throwable.getMessage());
                    }
                });
    }

    @Override
    protected void initData() {
        acount = new Acount(MyApplication.getContext());
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
                .load(R.drawable.login_bg_enable)
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
        mPresenter = new RegistAndLoginPresenterImpl(this);
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

        mPresenter.dologin(usrname.getText().toString(), password.getText().toString());
        Log.d(TAG, "dologin: username" + usrname + "   password" + "");

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

    public void SetAcount(String username, String password) {
        acount.setAccount(username, password);
    }
}
