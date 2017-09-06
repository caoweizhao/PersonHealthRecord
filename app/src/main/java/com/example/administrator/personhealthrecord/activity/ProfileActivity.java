package com.example.administrator.personhealthrecord.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Outline;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.UserInfoBean;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.util.AnimateUtil;
import com.example.administrator.personhealthrecord.util.DialogUtil;
import com.example.administrator.personhealthrecord.util.RetrofitUtil;
import com.google.gson.Gson;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

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
    @BindView(R.id.profile_bg)
    ImageView mImageView;
    @BindView(R.id.credit_text)
    TextView mCreditText;

    @BindView(R.id.divider_view_1)
    View divider_view_1;
    @BindView(R.id.divider_view_2)
    View divider_view_2;
    @BindView(R.id.divider_view_3)
    View divider_view_3;
    @BindView(R.id.divider_view_4)
    View divider_view_4;
    @BindView(R.id.divider_view_5)
    View divider_view_5;


    DisplayMetrics mMetrics = new DisplayMetrics();
    boolean isEditState;
    private ValueAnimator mUndoAnimator;
    private ObjectAnimator mNameAnimator;
    private ObjectAnimator mGenderAnimator;
    private ObjectAnimator mPhoneAnimator;
    private ObjectAnimator mAddressAnimator;
    private ObjectAnimator mCreditAnimator;

    private ProfileService mService;
    private Disposable mDisposable;
    private UserInfoBean mUserInfoBean;

    private SweetAlertDialog mLoadingDialog;


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_profile;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mService = RetrofitUtil.getRetrofit().create(ProfileService.class);
        sm.setStatusBarTintEnabled(false);
        initToolbar("", true, null);
        loadProfileBackGround(mImageView);
        initAvator();
        mLoadingDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        mLoadingDialog.setCancelable(false);
        mLoadingDialog.setTitleText("正在处理...");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLoadingDialog.show();
        getProfile();
    }

    private void getProfile() {
        if (Contract.IsLogin.equals(Contract.Login)) {
            mService.getSelfInfo(Contract.cookie)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ResponseBody>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            mDisposable = d;
                        }

                        @Override
                        public void onNext(ResponseBody value) {
                            try {
                                JSONObject jsonObject;
                                jsonObject = new JSONObject(value.string());
                                if (jsonObject.get("status").equals("success")) {
                                    Gson gson = new Gson();
                                    mUserInfoBean = gson.fromJson(jsonObject.get("object").toString(),
                                            UserInfoBean.class);
                                    initValue();
                                }
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            initAvator();
                            mLoadingDialog.dismiss();
                            Toast.makeText(ProfileActivity.this, "连接失败", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onComplete() {
                            initAvator();
                            mLoadingDialog.dismiss();
                        }
                    });
        } else {
            SweetAlertDialog dialog = DialogUtil.getLoginDialog(this);
            dialog.show();
        }
    }

    /**
     * 赋值
     */
    private void initValue() {
        mProfileNameTextView.setText(mUserInfoBean.getName());
        mProfileGenderTextView.setText(mUserInfoBean.getGender());
        mProfilePhoneTextView.setText(mUserInfoBean.getPhoneNumber());
        mProfileAddressTextView.setText(mUserInfoBean.getAddress());
        mProfileCreditTextView.setText(mUserInfoBean.getCredits());
        mProfileAgeText.setText(mUserInfoBean.getAge());

        mProfileNameEditText.setText(mUserInfoBean.getName());
        mProfileNameEditText.setSelection(mUserInfoBean.getName().length());
        mProfileAddressEditText.setText(mUserInfoBean.getAddress());
        mProfileAgeEditText.setText(mUserInfoBean.getAge());
        mProfileGenderEditText.setText(mUserInfoBean.getGender());
        mProfilePhoneEditText.setText(mUserInfoBean.getPhoneNumber());
    }

    /**
     * 设置背景
     *
     * @param mImageView
     */
    private void loadProfileBackGround(ImageView mImageView) {
        Glide.with(this)
                .load(R.drawable.profile_bg)
                .into(mImageView);
    }

    @Override
    protected void initData() {
        getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
        mProfileUndoFab.setEnabled(false);
    }

    @Override
    protected void initEvents() {
        super.initEvents();

        RxView.clicks(mProfileAvator).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Intent i = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 0);
            }
        });

        RxTextView.textChanges(mProfileNameEditText).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence sequence) throws Exception {
                if (sequence != null && mUserInfoBean != null) {
                    mUserInfoBean.setName(sequence.toString());
                }
            }
        });

        RxTextView.textChanges(mProfileGenderEditText).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence sequence) throws Exception {
                if (sequence != null && mUserInfoBean != null) {
                    mUserInfoBean.setGender(sequence.toString());
                }
            }
        });

        RxTextView.textChanges(mProfilePhoneEditText).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence sequence) throws Exception {
                if (sequence != null && mUserInfoBean != null) {
                    mUserInfoBean.setPhoneNumber(sequence.toString());
                }
            }
        });
        RxTextView.textChanges(mProfileAddressEditText).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence sequence) throws Exception {
                if (sequence != null && mUserInfoBean != null) {
                    mUserInfoBean.setAddress(sequence.toString());
                }
            }
        });
        RxTextView.textChanges(mProfileAgeEditText).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence sequence) throws Exception {
                Log.d("ProfileActivity", "accept" + sequence);
                if (sequence != null && mUserInfoBean != null) {
                    mUserInfoBean.setAge(sequence.toString());
                    Log.d("ProfileActivity", "accept" + sequence);
                }
            }
        });

        //编辑按钮点击
        RxView.clicks(mProfileEditFab)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Log.d("ProfileActivity", "accept" + isEditState);
                        if (isEditState) {
                            /**
                             * 当前为编辑状态，切换为展示状态
                             */
                            mLoadingDialog.show();
                            mService.updateProfile(Contract.cookie, mUserInfoBean)
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribe(new Observer<ResponseBody>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {
                                            mDisposable = d;
                                        }

                                        @Override
                                        public void onNext(ResponseBody value) {
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            mLoadingDialog.dismiss();
                                            Toast.makeText(ProfileActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onComplete() {
                                            mLoadingDialog.dismiss();
                                            if (mProfileUndoFab.getScaleX() == 1) {
                                                toggleEditState();
                                                initValue();
                                                Toast.makeText(ProfileActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        } else {
                            /**
                             * 当前为展示状态，切换为编辑状态
                             */
                            if (mProfileUndoFab.getScaleX() == 0) {
                                toggleEditState();
                            }
                        }
                    }
                });
        //撤销按钮点击
        mProfileUndoFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //当前为编辑状态
                toggleEditState();
            }
        });
    }

    /**
     * 切换状态（展示、编辑）
     */
    private void toggleEditState() {
        if (isEditState) {
            reverseAnimate();
            isEditState = false;
            showDividerView();
        } else {
            isEditState = true;
            startAnimate();
            dismissDividerView();
        }
    }

    /**
     * 展示状态下，显示分割线
     */
    private void showDividerView() {
        divider_view_1.setVisibility(View.VISIBLE);
        divider_view_2.setVisibility(View.VISIBLE);
        divider_view_3.setVisibility(View.VISIBLE);
        divider_view_4.setVisibility(View.VISIBLE);
        divider_view_5.setVisibility(View.VISIBLE);
    }

    /**
     * 编辑状态下，隐藏分割线
     */
    private void dismissDividerView() {
        divider_view_1.setVisibility(View.INVISIBLE);
        divider_view_2.setVisibility(View.INVISIBLE);
        divider_view_3.setVisibility(View.INVISIBLE);
        divider_view_4.setVisibility(View.INVISIBLE);
        divider_view_5.setVisibility(View.INVISIBLE);
    }

    /**
     * 切换到编辑状态的动画
     */
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
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    if (mProfileUndoFab.getScaleX() == 0) {
                        mProfileUndoFab.setEnabled(false);
                    }
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    mProfileUndoFab.setEnabled(true);
                }
            });
            mUndoAnimator.setInterpolator(new LinearInterpolator());
            mUndoAnimator.start();
        }

    }

    /**
     * 切换到显示状态的动画
     */
    private void reverseAnimate() {
        mProfileEditFab.setScaleX(0);
        mProfileEditFab.setScaleY(0);
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
        String imageUrl = "";
        if (mUserInfoBean != null) {
            imageUrl = Contract.UserInfoBase + mUserInfoBean.getIconImage();
        }
        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.chat_left_human)
                .error(R.drawable.chat_left_human)
                .into(mProfileAvator);

        /**
         * 使用ViewOutlineProvider设置圆形
         */
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
        objectAnimator.setDuration(750);
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
            /*@Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                if (editText.getId() == R.id.profile_age_edit_text) {
                    editText.setText(mProfileAgeText.getText());
                } else {
                    editText.setText(mHealthyNewsDetailTextView.getText());
                }
            }*/

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

    @Override
    protected void onDestroy() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
        if (mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            uploadImage(picturePath);
        }
    }

    @Override
    public void onBackPressed() {
        if (isEditState) {
            toggleEditState();
        } else {
            mProfileEditFab.setVisibility(View.GONE);
            mProfileUndoFab.setVisibility(View.GONE);
            super.onBackPressed();
        }
    }

    /**
     * 上传头像
     *
     * @param url
     */
    private void uploadImage(String url) {
        mLoadingDialog.show();
        File file;
        file = new File(url);
        //创建RequestBody  用于封装构建文件
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/from-data"), file);
        //和后端协商key,使用icon
        MultipartBody.Part body = MultipartBody.Part.createFormData("icon", file.getName(), requestFile);

        //添加描述
        String descriptionString = "hello,这是文件描述";
        RequestBody description = RequestBody.create(MediaType.parse("multipart/from-data"), descriptionString);
        Call<ResponseBody> call = mService.updateAvator(Contract.cookie, body, description);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG, "onResponse: " + "respone is nulllllllllllllllll" + response.toString());
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    if (jsonObject.get("status").equals("success")) {
                        Gson gson = new Gson();
                        mUserInfoBean = gson.fromJson(jsonObject.get("object").toString(), UserInfoBean.class);
                        initAvator();
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                mLoadingDialog.dismiss();
            }
        });
    }

    interface ProfileService {

        @PUT("user_info/update")
        Observable<ResponseBody> updateProfile(@Header("Cookie") String cookie,
                                               @Body UserInfoBean userInfoBean);

        @GET("user_info/search")
        Observable<ResponseBody> getSelfInfo(@Header("Cookie") String cookie);

        @Multipart
        @POST("user_info/updateImage")
        Call<ResponseBody> updateAvator(@Header("Cookie") String cookie, @Part MultipartBody.Part icon,
                                        @Part("description") RequestBody description);
    }
}
