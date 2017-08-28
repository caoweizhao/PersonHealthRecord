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
import android.view.LayoutInflater;
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
import com.example.administrator.personhealthrecord.base.BaseFragment;
import com.example.administrator.personhealthrecord.mvp.main.MainActivity;
import com.example.administrator.personhealthrecord.util.AnimateUtil;
import com.example.administrator.personhealthrecord.util.ColorUtil;
import com.youth.banner.transformer.ZoomOutSlideTransformer;

import butterknife.BindView;

import static android.graphics.Color.parseColor;
import static com.example.administrator.personhealthrecord.contract.Contract.colors;
import static com.example.administrator.personhealthrecord.contract.Contract.colorsLighter;
import static com.example.administrator.personhealthrecord.contract.Contract.colorsStr;
import static com.example.administrator.personhealthrecord.util.ColorUtil.getCurrentColor;

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
    public CollapsingToolbarLayout mCollapsingToolbarLayout;

    private int[] mImages = new int[]{R.drawable.ic_news_icon, R.drawable.ic_medicine_icon,
            R.drawable.ic_disease_icon, R.drawable.ic_immune_icon};
    private Matrix mImageMatrix;

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
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_social_page, container, false);
    }

    ValueAnimator animator;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Glide.with(this)
                .load(R.drawable.community2)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .listener(new RequestListener<Integer, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, Integer model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, Integer model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        startImageAnim();
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
    }

    private void initImageView() {
        mImageMatrix = new Matrix();
        animator = ValueAnimator.ofFloat(1, 1.5f)
                .setDuration(15000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float s = (float) animation.getAnimatedValue();
                if (mImageView != null) {
                    mImageMatrix.setScale(s, s, mImageView.getWidth() * 1.0f / 2, mImageView.getHeight() * 1.0f / 2);
                    mImageView.setImageMatrix(mImageMatrix);
                }
            }
        });
        animator.setRepeatCount(Integer.MAX_VALUE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.start();
    }

    public void startImageAnim() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (animator != null && animator.isPaused()) {
                animator.resume();
            } else {
                initImageView();
            }
        } else {
            if (animator != null && !animator.isRunning()) {
                animator.start();
            } else {
                initImageView();
            }
        }
    }

    public void stopImageAnim() {
        if (animator != null && animator.isRunning()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                animator.pause();
            } else {
                animator.cancel();
            }
        }
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
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                final int pos = tab.getPosition();
                mViewPager.setCurrentItem(pos, false);
                ((MainActivity) getActivity()).setStatusBarTint(colors[pos]);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mCollapsingToolbarLayout.setBackgroundColor(parseColor(colorsStr[pos]));
                    mCollapsingToolbarLayout.setBackgroundTintMode(PorterDuff.Mode.SRC);
                    mCollapsingToolbarLayout.setContentScrimColor(parseColor(colorsStr[pos]));
                }
                mFloatingActionButton.setBackgroundTintList(ColorStateList.valueOf(colorsLighter[pos]));
                mFloatingActionButton.setImageResource(mImages[pos]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int color;
                /**
                 * 0->1
                 */
                int curPos = getCurrentPosition();
                if ((position == 0 && curPos == 0) ||
                        (position == 1 && curPos == 1) ||
                        (position == 2 && curPos == 2)) {
                    AnimateUtil.scaleHide(mFloatingActionButton, positionOffset);
                    color = ColorUtil.getCurrentColor(positionOffset, Color.parseColor(colorsStr[curPos]),
                            Color.parseColor(colorsStr[3]));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mCollapsingToolbarLayout.setBackgroundColor(color);
                        mCollapsingToolbarLayout.setBackgroundTintMode(PorterDuff.Mode.SRC);
                        mCollapsingToolbarLayout.setContentScrimColor(color);
                        mFloatingActionButton.setBackgroundTintList(ColorStateList.valueOf(color));
                        ((MainActivity) getActivity()).setBottomBarTint(color);
                        ((MainActivity) getActivity()).refreshBottom();
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
                        mFloatingActionButton.setBackgroundTintList(ColorStateList.valueOf(color));
                        ((MainActivity) getActivity()).setBottomBarTint(color);
                        ((MainActivity) getActivity()).refreshBottom();
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
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mViewPager.setPageTransformer(true, new ZoomOutSlideTransformer());
    }

    public int getCurrentPosition() {
        if (mTabLayout != null) {
            return mTabLayout.getSelectedTabPosition() >= 0 ? mTabLayout.getSelectedTabPosition() : 0;
        }
        return 0;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (animator != null && animator.isRunning()) {
            animator.cancel();
        }
    }
}

