package com.example.administrator.personhealthrecord.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.UserInfoBean;
import com.example.administrator.personhealthrecord.util.AnimateUtil;

import butterknife.BindView;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.PUT;

import static android.animation.ObjectAnimator.ofFloat;

/**
 * Created by Administrator on 2017-8-3.
 */

public class ProfileActivity extends BaseActivity {


    @BindView(R.id.profile_avator)
    ImageView mProfileAvator;
    @BindView(R.id.profile_name_edit_text)
    EditText mProfileNameEditText;
    @BindView(R.id.profile_name_text_view)
    TextView mProfileNameTextView;
    @BindView(R.id.profile_gender_edit_text)
    EditText mProfileGenderEditText;
    @BindView(R.id.profile_gender_text_view)
    TextView mProfileGenderTextView;
    @BindView(R.id.profile_phone_edit_text)
    EditText mProfilePhoneEditText;
    @BindView(R.id.profile_phone_text_view)
    TextView mProfilePhoneTextView;
    @BindView(R.id.profile_address_edit_text)
    EditText mProfileAddressEditText;
    @BindView(R.id.profile_address_text_view)
    TextView mProfileAddressTextView;
    @BindView(R.id.profile_age_edit_text)
    EditText mProfileAgeEditText;
    @BindView(R.id.profile_credit_text_view)
    TextView mProfileCreditTextView;
    @BindView(R.id.cardView)
    CardView mCardView;
    @BindView(R.id.profile_edit_fab)
    FloatingActionButton mProfileEditFab;
    @BindView(R.id.profile_undo_fab)
    FloatingActionButton mProfileUndoFab;
    @BindView(R.id.profile_age_text)
    TextView mProfileAgeText;

    @BindView(R.id.credit_text)
    TextView mCreditText;

    DisplayMetrics mMetrics = new DisplayMetrics();
    boolean isEditState;
    private ValueAnimator mUndoAnimator;
    private ObjectAnimator mNameAnimator;
    private ObjectAnimator mGenderAnimator;
    private ObjectAnimator mPhoneAnimator;
    private ObjectAnimator mAddressAnimator;
    private ObjectAnimator mCreditAnimator;
    private AnimatorSet mAnimatorSet;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_profile;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sm.setStatusBarTintEnabled(false);
        initToolbar("", true, null);
        ImageView mImageView = (ImageView) findViewById(R.id.profile_bg);
        Glide.with(this)
                .load(R.drawable.profile_bg)
                .into(mImageView);
        initAvator();

    }

    @Override
    protected void initData() {
        getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        mProfileUndoFab.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (mProfileUndoFab.getScaleX() == 0) {
                    mProfileUndoFab.setVisibility(View.GONE);
                    Log.d("ProfileActivity", "gone");
                } else {
                    mProfileUndoFab.setVisibility(View.VISIBLE);
                }
            }
        });
        mProfileUndoFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reverseAnimate();
                isEditState = false;
            }
        });
        mProfileEditFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEditState) {
                    /**
                     * 当前为编辑状态，切换为展示状态
                     */
                    isEditState = false;
                    reverseAnimate();
                } else {
                    /**
                     * 当前为展示状态，切换为编辑状态
                     */
                    isEditState = true;
                    startAnimate();
                }
            }
        });
    }

    private void startAnimate() {
        AnimateUtil.scaleHide(mProfileEditFab, new ViewPropertyAnimatorListener() {

            @Override
            public void onAnimationStart(View view) {

            }

            @Override
            public void onAnimationEnd(View view) {
                mProfileEditFab.setImageResource(R.drawable.ic_done_black_24dp);
                AnimateUtil.scaleShow(mProfileEditFab, null);
            }

            @Override
            public void onAnimationCancel(View view) {

            }
        });

        if (mNameAnimator == null || !mNameAnimator.isRunning()) {
            mNameAnimator = getAnimator(mProfileNameTextView, mProfileNameEditText);
            mNameAnimator.start();
        }
        if (mGenderAnimator == null || !mGenderAnimator.isRunning()) {
            mGenderAnimator = getAnimator(mProfileGenderTextView, mProfileGenderEditText);
            mGenderAnimator.start();
        }
        if (mPhoneAnimator == null || !mPhoneAnimator.isRunning()) {
            mPhoneAnimator = getAnimator(mProfilePhoneTextView, mProfilePhoneEditText);
            mPhoneAnimator.start();
        }
        if (mAddressAnimator == null || !mAddressAnimator.isRunning()) {
            mAddressAnimator = getAnimator(mProfileAddressTextView, mProfileAddressEditText);
            mAddressAnimator.start();
        }
        if (mCreditAnimator == null || !mCreditAnimator.isRunning()) {
            mCreditAnimator = getAnimator(mProfileCreditTextView, mProfileAgeEditText);
            mCreditAnimator.start();
        }

        if (mUndoAnimator == null || !mUndoAnimator.isRunning()) {
            mCreditAnimator = getAnimator(mProfileCreditTextView, mProfileAgeEditText);
            mUndoAnimator = ofFloat(mProfileUndoFab, "translationX", 0,
                    -TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, mMetrics));
            mUndoAnimator.setDuration(1000);
            mUndoAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float scale = animation.getAnimatedFraction();
                    mProfileUndoFab.setScaleX(scale);
                    mProfileUndoFab.setScaleY(scale);
                    mProfileUndoFab.setAlpha(255 * scale);
                }
            });
            mUndoAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    mProfileUndoFab.setVisibility(View.VISIBLE);
                }
            });
            mUndoAnimator.setInterpolator(new LinearInterpolator());
            mUndoAnimator.start();
        }

    }

    private void reverseAnimate() {
        mProfileEditFab.setScaleX(0);
        AnimateUtil.scaleShow(mProfileEditFab, null);
        mProfileEditFab.setImageResource(R.drawable.ic_edit);
        mUndoAnimator.reverse();
        mNameAnimator.reverse();
        mGenderAnimator.reverse();
        mCreditAnimator.reverse();
        mPhoneAnimator.reverse();
        mAddressAnimator.reverse();
    }

    /**
     * 设置圆形头像
     */
    private void initAvator() {
        mProfileAvator.post(new Runnable() {
            @Override
            public void run() {
                final int width = mProfileAvator.getWidth();
                final int height = mProfileAvator.getHeight();
                int length = Math.min(width, height);

                int centerX = width / 2;
                int centerY = height / 2;

                final int left;
                final int top;
                final int right;
                final int bottom;

                if (length == width) {
                    left = 0;
                    top = centerY - length / 2;
                    right = width;
                    bottom = centerY + length / 2;
                } else {
                    left = centerX - length / 2;
                    top = 0;
                    right = centerX + length / 2;
                    bottom = height;
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ViewOutlineProvider outlineProvider = new ViewOutlineProvider() {
                        @Override
                        public void getOutline(View view, Outline outline) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                Log.d("MainActivity", "getOutline" + view.getMeasuredWidth() + ":" + view.getMeasuredHeight());
                                outline.setOval(left, top, right, bottom);
                            }
                        }
                    };
                    mProfileAvator.setClipToOutline(true);
                    mProfileAvator.setOutlineProvider(outlineProvider);
                }
            }
        });
    }

    private ObjectAnimator getAnimator(final TextView textView, final EditText editText) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mProfileAddressTextView,
                "alpha", 255, 0);
        objectAnimator.setDuration(1000);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float scale = animation.getAnimatedFraction();
                textView.setScaleX(1 - scale);
                textView.setScaleY(1 - scale);
                editText.setAlpha(255 * scale);
                editText.setScaleX(scale);
                editText.setScaleY(scale);
            }
        });
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                if (editText.getId() == R.id.profile_age_edit_text) {
                    editText.setText(mProfileAgeText.getText());
                } else {
                    editText.setText(textView.getText());
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (editText.getScaleX() == 0) {
                    editText.setVisibility(View.GONE);
                    if (editText.getId() == R.id.profile_age_edit_text) {
                        mCreditText.setText("积分：");
                    }
                } else {
                    editText.setVisibility(View.VISIBLE);
                    if (editText.getId() == R.id.profile_age_edit_text) {
                        mCreditText.setText("年龄：");
                    }
                }
            }
        });
        return objectAnimator;
    }

    interface ProfileService {

        @PUT("")
        Observable<ResponseBody> updateProfile(@Body UserInfoBean userInfoBean);
    }
}
