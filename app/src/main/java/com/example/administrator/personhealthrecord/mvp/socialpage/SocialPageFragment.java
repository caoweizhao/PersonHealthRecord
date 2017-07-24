package com.example.administrator.personhealthrecord.mvp.socialpage;


import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.adapter.SocialPageViewPagerAdapter;
import com.example.administrator.personhealthrecord.mvp.base.BaseFragment;
import com.example.administrator.personhealthrecord.mvp.main.MainActivity;
import com.example.administrator.personhealthrecord.util.AnimateUtil;

import butterknife.BindView;

import static android.graphics.Color.parseColor;
import static com.example.administrator.personhealthrecord.contract.Contract.colors;
import static com.example.administrator.personhealthrecord.contract.Contract.colorsLighter;
import static com.example.administrator.personhealthrecord.contract.Contract.colorsStr;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SocialPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SocialPageFragment extends BaseFragment {


    @BindView(R.id.social_page_tabLayout)
    TabLayout mTabLayout;

    @BindView(R.id.social_page_viewPager)
    ViewPager mViewPager;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.social_page_img_bg)
    ImageView mImageView;
    @BindView(R.id.social_page_fab)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.social_page_collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;


    private int[] imgs = new int[]{R.drawable.ic_news_icon, R.drawable.ic_medicine_icon,
            R.drawable.ic_disease_icon, R.drawable.ic_immune_icon};
    private Matrix mImageMatrix;
    private float[] matrixValues = new float[9];

    public SocialPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SocialPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SocialPageFragment newInstance() {
        return new SocialPageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_social_page, container, false);
        return view;
    }

    ValueAnimator animator;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Glide.with(this)
                .load(R.drawable.community2)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .listener(new RequestListener<Integer, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, Integer model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, Integer model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        initImageView(resource);
                        return false;
                    }
                })
                .into(mImageView);
        FragmentManager fm = getChildFragmentManager();
        mViewPager.setAdapter(new SocialPageViewPagerAdapter(fm));
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager);
        //initToolbar("社区");
        setUpWithActivity(view);
        mImageMatrix = new Matrix();
        animator = ValueAnimator.ofFloat(1, 1.5f)
                .setDuration(6000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentScale = getScale();
                float value = (float) animation.getAnimatedValue();
               /* mImageMatrix.postScale(value * currentScale, value * currentScale,
                        mImageView.getWidth() / 2, mImageView.getHeight() / 2);
                mImageView.setImageMatrix(mImageMatrix);*/
            }
        });
        animator.setRepeatCount(Integer.MAX_VALUE);
        animator.start();
    }

    private void initImageView(GlideDrawable d) {
        if (d == null)
            return;
        int width = mImageView.getWidth();
        int height = mImageView.getHeight();
        // 拿到图片的宽和高
        int dw = d.getIntrinsicWidth();
        int dh = d.getIntrinsicHeight();
        float scale = 1.0f;
        // 如果图片的宽或者高大于屏幕，则缩放至屏幕的宽或者高
        if (dw > width && dh <= height) {
            scale = height * 1.0f / dh;
        }
        if (dh > height && dw <= width) {
            scale = width * 1.0f / dw;
        }
        // 如果宽和高都大于屏幕，则让其按按比例适应屏幕大小
        if (dw > width && dh > height) {
            scale = Math.min(dw * 1.0f / width, dh * 1.0f / height);
        }
        if (dw < width && dh < height) {
            scale = Math.max(width * 1.0f / dw, height * 1.0f / dh);
        }

        Log.d("InitImageView", "scale:" + scale + "dw:" + dw + "dh:" + dh + "width:" + width + "height:" + height);
        // 图片移动至屏幕中心
        if (mImageMatrix == null) {
            mImageMatrix = new Matrix();
        }
        mImageMatrix.postTranslate((width - dw) / 2, (height - dh) / 2);
        mImageMatrix
                .postScale(scale, scale, mImageView.getWidth() / 2, mImageView.getHeight() / 2);
        mImageView.setImageMatrix(mImageMatrix);

        final float s = getScale();
        Log.d("", "scale:" + s);
        animator = ValueAnimator.ofFloat(1, 1.1f, 0.9091f, 1)
                .setDuration(6000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float s = (float) animation.getAnimatedValue();
                Log.d("SocialPageFragment", "s" + s);
                mImageMatrix.postScale(s, s, mImageView.getWidth() / 2, mImageView.getHeight() / 2);
                mImageView.setImageMatrix(mImageMatrix);
            }
        });
        animator.setRepeatCount(Integer.MAX_VALUE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.start();
    }

    public final float getScale() {
        mImageMatrix.getValues(matrixValues);
        return matrixValues[Matrix.MSCALE_X];
    }

    @Override
    protected void initEvent() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                final int pos = tab.getPosition();

                ((MainActivity) getActivity()).setStatusBarTint(colors[pos]);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mCollapsingToolbarLayout.setBackgroundColor(parseColor(colorsStr[pos]));
                    mCollapsingToolbarLayout.setBackgroundTintMode(PorterDuff.Mode.SRC);
                    mCollapsingToolbarLayout.setContentScrimColor(parseColor(colorsStr[pos]));
                }
                mViewPager.setCurrentItem(pos);
                mFloatingActionButton.setBackgroundTintList(ColorStateList.valueOf(colorsLighter[pos]));
                mFloatingActionButton.setImageResource(imgs[pos]);

                AnimateUtil.scaleShow(mFloatingActionButton, null);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                Log.d("SocialPageFragment", "position:" + position + "cp:" + getCurrentPosition()
                        + "offset:" + positionOffset);
                int color = 0;
                /**
                 * 0->1
                 */
                int curPos = getCurrentPosition();
                if ((position == 0 && curPos == 0) ||
                        (position == 1 && curPos == 1) ||
                        (position == 2 && curPos == 2)) {
                    AnimateUtil.scaleHide(mFloatingActionButton, positionOffset);
                    color = getCurrentColor(positionOffset, Color.parseColor(colorsStr[curPos]),
                            Color.parseColor(colorsStr[3]));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mCollapsingToolbarLayout.setBackgroundColor(color);
                        mCollapsingToolbarLayout.setBackgroundTintMode(PorterDuff.Mode.SRC);
                        mCollapsingToolbarLayout.setContentScrimColor(color);
                        ((MainActivity) getActivity()).setStatusBarTint(color);
                    }
                }
                /**
                 * 1->2
                 *//*
                else if (position == 1 && getCurrentPosition() == 1) {
                    AnimateUtil.scaleHide(mFloatingActionButton, positionOffset);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        int color = getCurrentColor(positionOffset, Color.parseColor(colorsStr[position]),
                                Color.parseColor(colorsStr[position + 1]));
                        mCollapsingToolbarLayout.setBackgroundColor(color);
                        mCollapsingToolbarLayout.setBackgroundTintMode(PorterDuff.Mode.SRC);
                        mCollapsingToolbarLayout.setContentScrimColor(color);
                    }
                }
                *//**
                 * 2->3
                 *//*
                else if (position == 2 && getCurrentPosition() == 2) {
                    AnimateUtil.scaleHide(mFloatingActionButton, positionOffset);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        int color = getCurrentColor(positionOffset, Color.parseColor(colorsStr[position]),
                                Color.parseColor(colorsStr[position + 1]));
                        mCollapsingToolbarLayout.setBackgroundColor(color);
                        mCollapsingToolbarLayout.setBackgroundTintMode(PorterDuff.Mode.SRC);
                        mCollapsingToolbarLayout.setContentScrimColor(color);
                    }
                }*/
                /**
                 * 3->2
                 */
                else if ((position == 2 && getCurrentPosition() == 3 ||
                        position == 1 && getCurrentPosition() == 2 ||
                        position == 0 && getCurrentPosition() == 1)) {
                    AnimateUtil.scaleHide(mFloatingActionButton, 1 - positionOffset);
                    color = getCurrentColor(1 - positionOffset, Color.parseColor(colorsStr[position + 1]),
                            Color.parseColor(colorsStr[position]));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mCollapsingToolbarLayout.setBackgroundColor(color);
                        mCollapsingToolbarLayout.setBackgroundTintMode(PorterDuff.Mode.SRC);
                        mCollapsingToolbarLayout.setContentScrimColor(color);
                        ((MainActivity) getActivity()).setStatusBarTint(color);
                    }
                }


                /**
                 * 2->1
                 *//*
                else if (position == 1 && getCurrentPosition() == 2) {
                    AnimateUtil.scaleHide(mFloatingActionButton, 1 - positionOffset);
                }
                *//**
                 * 1->0
                 *//*
                else if (position == 0 && getCurrentPosition() == 1) {
                    AnimateUtil.scaleHide(mFloatingActionButton, 1 - positionOffset);
                }*/

            }

            @Override
            public void onPageSelected(int position) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mCollapsingToolbarLayout.setBackgroundColor(colors[position]);
                    mCollapsingToolbarLayout.setBackgroundTintMode(PorterDuff.Mode.SRC);
                    mCollapsingToolbarLayout.setContentScrimColor(colors[position]);
                    ((MainActivity) getActivity()).setStatusBarTint(colors[position]);


                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setUpWithActivity(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((MainActivity) getActivity()).setUpWithToolbar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).openMenu();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    @Override
    protected void initData() {

    }

    public int getCurrentPosition() {
        return mTabLayout.getSelectedTabPosition();
    }

    private int getCurrentColor(float fraction, int startColor, int endColor) {
        int redCurrent;
        int blueCurrent;
        int greenCurrent;
        int alphaCurrent;

        int redStart = Color.red(startColor);
        int blueStart = Color.blue(startColor);
        int greenStart = Color.green(startColor);
        int alphaStart = Color.alpha(startColor);

        int redEnd = Color.red(endColor);
        int blueEnd = Color.blue(endColor);
        int greenEnd = Color.green(endColor);
        int alphaEnd = Color.alpha(endColor);

        int redDifference = redEnd - redStart;
        int blueDifference = blueEnd - blueStart;
        int greenDifference = greenEnd - greenStart;
        int alphaDifference = alphaEnd - alphaStart;

        redCurrent = (int) (redStart + fraction * redDifference);
        blueCurrent = (int) (blueStart + fraction * blueDifference);
        greenCurrent = (int) (greenStart + fraction * greenDifference);
        alphaCurrent = (int) (alphaStart + fraction * alphaDifference);

        return Color.argb(alphaCurrent, redCurrent, greenCurrent, blueCurrent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (animator != null && animator.isRunning()) {
            animator.cancel();
        }
    }
}

