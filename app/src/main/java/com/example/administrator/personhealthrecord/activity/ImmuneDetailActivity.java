package com.example.administrator.personhealthrecord.activity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.ImmuneBean;
import com.example.administrator.personhealthrecord.contract.Contract;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Administrator on 2017-8-2.
 */

public class ImmuneDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.function_view_stub)
    ViewStub mFunctionViewStub;
    @BindView(R.id.reason_view_stub)
    ViewStub mReasonViewStub;
    @BindView(R.id.attention_view_stub)
    ViewStub mAttentionViewStub;
    @BindView(R.id.group_view_stub)
    ViewStub mGroupViewStub;

    @BindView(R.id.summary_view_top)
    TextView mSummaryViewTop;
    @BindView(R.id.function_view_top)
    TextView mFunctionViewTop;
    @BindView(R.id.reason_view_top)
    TextView mReasonViewTop;
    @BindView(R.id.group_view_top)
    TextView mGroupViewTop;
    @BindView(R.id.attention_view_top)
    TextView mAttentionViewTop;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.immune_detail_image)
    ImageView mImageView;
    @BindView(R.id.summary_view)
    TextView mSummaryView;

    TextView mFunctionView;
    TextView mAttentionView;
    TextView mGroupView;
    TextView mReasonView;

    private Drawable mSummaryDrawable;
    private Drawable mFunctionDrawable;
    private Drawable mReasonDrawable;
    private Drawable mGroupDrawable;
    private Drawable mAttentionDrawable;
    private float mSummaryStart = 10000;
    private float mFunctionStart = 0;
    private float mReasonStart = 0;
    private float mGroupStart = 0;
    private float mAttentionStart = 0;
    private float mSummaryEnd = 0;
    private float mFunctionEnd = 10000;
    private float mReasonEnd = 10000;
    private float mAttentionEnd = 10000;
    private float mGroupEnd = 10000;
    private ValueAnimator mSummaryAnimator;
    private ValueAnimator mFunctionAnimator;
    private ValueAnimator mReasonAnimator;
    private ValueAnimator mGroupAnimator;
    private ValueAnimator mAttentionAnimator;

    private ImmuneBean mImmuneBean;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_immune_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar("炭疽疫苗", true, null);
        sm.setStatusBarTintEnabled(false);
        initDrawables();
        Intent intent = getIntent();
        if (intent != null) {
            mImmuneBean = intent.getParcelableExtra("data");
            if (mImmuneBean == null) {
                SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE);
                dialog.setTitleText("出现未知错误");
                dialog.setCancelable(false);
                dialog.setConfirmText("确定")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismiss();
                                finish();
                            }
                        });
                dialog.show();
            } else {
                mToolbar.setTitle(mImmuneBean.getName());
                mSummaryView.setText(mImmuneBean.getSummary());
                mSummaryDrawable.setLevel(10000);

                String mImageURL;
                if (mImmuneBean.getImageUrl().contains("http")) {
                    mImageURL = mImmuneBean.getImageUrl();
                } else {
                    mImageURL = Contract.ImmuneBase + mImmuneBean.getImageUrl();
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Glide.with(this)
                            .load(mImageURL)
                            .asBitmap()
                            .centerCrop()
                            .placeholder(R.mipmap.ic_launcher_round)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .listener(new RequestListener<String, Bitmap>() {
                                @Override
                                public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                    return false;
                                }
                            })
                            .into(mImageView);
                }
            }
        }
    }

    private void initDrawables() {
        mSummaryDrawable = mSummaryViewTop.getCompoundDrawables()[2];
        mFunctionDrawable = mFunctionViewTop.getCompoundDrawables()[2];
        mReasonDrawable = mReasonViewTop.getCompoundDrawables()[2];
        mGroupDrawable = mGroupViewTop.getCompoundDrawables()[2];
        mAttentionDrawable = mAttentionViewTop.getCompoundDrawables()[2];
    }

    @OnClick({R.id.summary_view_top, R.id.function_view_top, R.id.group_view_top,
            R.id.attention_view_top, R.id.reason_view_top})
    @Override
    public void onClick(View v) {
        reset();
        switch (v.getId()) {
            case R.id.summary_view_top:
                handleSummary();
                break;
            case R.id.function_view_top:
                handleFunction();
                break;
            case R.id.attention_view_top:
                handleAttention();
                break;
            case R.id.reason_view_top:
                handleReason();
                break;
            case R.id.group_view_top:
                handleGroup();
                break;
            default:
                break;
        }
    }

    private void handleSummary() {
        if (mSummaryAnimator == null || !mSummaryAnimator.isRunning()) {
            mSummaryAnimator = ValueAnimator.ofFloat(mSummaryStart, mSummaryEnd)
                    .setDuration(500);
            mSummaryAnimator.setInterpolator(new LinearInterpolator());
            mSummaryAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mSummaryStart = ((Float) animation.getAnimatedValue());
                    mSummaryEnd = 10000 - mSummaryStart;
                    mSummaryDrawable.setLevel((int) mSummaryStart);
                }
            });
            mSummaryAnimator.start();

            toggleVisibility(mSummaryView);
        }
    }

    private void handleFunction() {
        if (mFunctionAnimator == null || !mFunctionAnimator.isRunning()) {
            mFunctionAnimator = ValueAnimator.ofFloat(mFunctionStart, mFunctionEnd)
                    .setDuration(500);
            mFunctionAnimator.setInterpolator(new LinearInterpolator());
            mFunctionAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mFunctionStart = ((Float) animation.getAnimatedValue());
                    mFunctionEnd = 10000 - mFunctionStart;
                    mFunctionDrawable.setLevel((int) mFunctionStart);
                }
            });
            mFunctionAnimator.start();

            if (mFunctionView == null) {
                mFunctionView = (TextView) mFunctionViewStub.inflate();
                mFunctionView.setText(mImmuneBean.getFunction());
            } else {
                toggleVisibility(mFunctionView);
            }
        }
    }

    private void handleReason() {
        if (mReasonAnimator == null || !mReasonAnimator.isRunning()) {
            mReasonAnimator = ValueAnimator.ofFloat(mReasonStart, mReasonEnd)
                    .setDuration(500);
            mReasonAnimator.setInterpolator(new LinearInterpolator());
            mReasonAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mReasonStart = ((Float) animation.getAnimatedValue());
                    mReasonEnd = 10000 - mReasonStart;
                    mReasonDrawable.setLevel((int) mReasonStart);
                }
            });
            mReasonAnimator.start();

            if (mReasonView == null) {
                mReasonView = (TextView) mReasonViewStub.inflate();
                mReasonView.setText(mImmuneBean.getReason());
            } else {
                toggleVisibility(mReasonView);
            }
        }
    }

    private void handleGroup() {
        if (mGroupAnimator == null || !mGroupAnimator.isRunning()) {
            mGroupAnimator = ValueAnimator.ofFloat(mGroupStart, mGroupEnd)
                    .setDuration(500);
            mGroupAnimator.setInterpolator(new LinearInterpolator());
            mGroupAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mGroupStart = ((Float) animation.getAnimatedValue());
                    mGroupEnd = 10000 - mGroupStart;
                    mGroupDrawable.setLevel((int) mGroupStart);
                }
            });
            mGroupAnimator.start();

            if (mGroupView == null) {
                mGroupView = (TextView) mGroupViewStub.inflate();
                mGroupView.setText(mImmuneBean.getGroupPeople());
            } else {
                toggleVisibility(mGroupView);
            }
        }
    }

    private void handleAttention() {
        if (mAttentionAnimator == null || !mAttentionAnimator.isRunning()) {
            mAttentionAnimator = ValueAnimator.ofFloat(mAttentionStart, mAttentionEnd)
                    .setDuration(500);
            mAttentionAnimator.setInterpolator(new LinearInterpolator());
            mAttentionAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mAttentionStart = ((Float) animation.getAnimatedValue());
                    mAttentionEnd = 10000 - mAttentionStart;
                    mAttentionDrawable.setLevel((int) mAttentionStart);
                }
            });
            mAttentionAnimator.start();

            if (mAttentionView == null) {
                mAttentionView = (TextView) mAttentionViewStub.inflate();
                mAttentionView.setText(mImmuneBean.getAttention());
            } else {
                toggleVisibility(mAttentionView);
            }
        }
    }

    /**
     * 显示切换
     *
     * @param view
     */
    private void toggleVisibility(View view) {
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSummaryAnimator != null && mSummaryAnimator.isRunning()) {
            mSummaryAnimator.cancel();
        }
        if (mFunctionAnimator != null && mFunctionAnimator.isRunning()) {
            mFunctionAnimator.cancel();
        }
        if (mReasonAnimator != null && mReasonAnimator.isRunning()) {
            mReasonAnimator.cancel();
        }
        if (mGroupAnimator != null && mGroupAnimator.isRunning()) {
            mGroupAnimator.cancel();
        }
        if (mAttentionAnimator != null && mAttentionAnimator.isRunning()) {
            mAttentionAnimator.cancel();
        }
        mSummaryDrawable.setLevel(0);
        mFunctionDrawable.setLevel(0);
        mReasonDrawable.setLevel(0);
        mGroupDrawable.setLevel(0);
        mAttentionDrawable.setLevel(0);
    }


    private void reset() {
        if (mSummaryStart != 0) {
            handleSummary();
        } else if (mFunctionStart != 0) {
            handleFunction();
        } else if (mReasonStart != 0) {
            handleReason();
        } else if (mGroupStart != 0) {
            handleGroup();
        } else if (mAttentionStart != 0) {
            handleAttention();
        }
    }

}
