package com.example.administrator.personhealthrecord.activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.bean.HospitalBean;
import com.example.administrator.personhealthrecord.bean.PackageBean;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.mvp.reserve.IReservePresenter;
import com.example.administrator.personhealthrecord.mvp.reserve.IReserveView;
import com.example.administrator.personhealthrecord.mvp.reserve.ReservePresenterImpl;
import com.example.administrator.personhealthrecord.util.DialogUtil;
import com.example.administrator.personhealthrecord.util.SnackBarUtil;
import com.example.administrator.personhealthrecord.util.ToastUtil;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by andy on 2017/7/28.
 */

public class ReserveNowActivity extends IReserveView implements View.OnClickListener {
    @BindView(R.id.healthtest_pic)
    ImageView mImageView;
    @BindView(R.id.health_check_item_linerlayout)
    LinearLayout mLinearLayout;
    @BindView(R.id.healthtest_name)
    TextView mName;
    @BindView(R.id.healthtest_details)
    TextView mSum;
    @BindView(R.id.healthtest_price)
    TextView mPrice;
    @BindView(R.id.healthtest_count)
    TextView mCount;
    @BindView(R.id.healthtest_date)
    TextView mDate;
    @BindView(R.id.input_Phone)
    EditText mEditPhoneNumber;
    @BindView(R.id.input_name)
    EditText mEditName;
    @BindView(R.id.health_check_select_time)
    Spinner mSelectTimeSpinner;
    @BindView(R.id.order_button)
    Button mOrderButton;
    private Date mStartTime;
    private Date mEndTime;
    private DateFormat mDateFormat;
    private int mPosition;
    private SweetAlertDialog pDialog;
    private PackageBean mPackageBean;

    @Override
    protected void initData() {
        super.initData();

        mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mPackageBean = getIntent().getParcelableExtra("package");
        Log.d(TAG, "initData: " + mPackageBean.getName());
        mSum.setText(mPackageBean.getSummary());
        mName.setText(mPackageBean.getName());
        mCount.setText("X1");
        if (Contract.IS_DISCOUNT)
            mPrice.setText("￥" + mPackageBean.getFavorablePrice());
        else
            mPrice.setText("￥" + mPackageBean.getPackagePrice());
        Log.d(TAG, "initData: " + mPackageBean.getImageUrl());
        String imageURL = Contract.PackageImageBase + mPackageBean.getImageUrl();
        Log.d(TAG, "initData: " + imageURL);
        Glide.with(this).load(imageURL)
                .into(mImageView);
        initToolbar("提交预约", true, null);
        mOrderButton.setEnabled(false);
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        mDate.setOnClickListener(this);
        mSelectTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        RxView.clicks(mOrderButton)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (Contract.IsLogin.equals(Contract.Login))//判断是否有登录
                        {
                            if (mEditName.getText().toString().equals("")
                                    || mEditPhoneNumber.getText().toString().equals(""))
                                SnackBarUtil.ShowSnackBar(mLinearLayout, "请填完个人信息", "OK!");
                            else {
                                setTime();
                                long time = System.currentTimeMillis();
                                final Calendar calendar = Calendar.getInstance();
                                calendar.setTimeInMillis(time);
                                Date date = calendar.getTime();
                                if (mStartTime.after(date))
                                    ReserveNow();
                                else
                                    ToastUtil.Toast("请选择当前时间之后的时间");
                            }

                        } else {
                            gotToLogin();
                        }
                    }
                });
        Observable<CharSequence> nameObservable = RxTextView.textChanges(mEditName);
        final Observable<CharSequence> phoneObservable = RxTextView.textChanges(mEditPhoneNumber);

        Observable.combineLatest(nameObservable, phoneObservable, new BiFunction<CharSequence, CharSequence, Boolean>() {

            @Override
            public Boolean apply(CharSequence charSequence, CharSequence charSequence2) throws Exception {
                return (mEditName.getText().toString().length() > 0) && (mEditPhoneNumber.getText().toString().length() == 11);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<Boolean>() {
                               @Override
                               public void accept(Boolean aBoolean) throws Exception {
                                   mOrderButton.setEnabled(aBoolean);
                               }
                           }
                        , new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.d(TAG, "accept: ");
                            }
                        });


    }

    @Override
    public IReservePresenter createPresenter() {
        return new ReservePresenterImpl();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void getPackageHospitals() {

    }

    @Override
    public void updateHospitals(List<HospitalBean> list) {

    }

    @Override
    public void getPackage(int id) {

    }

    @Override
    public void updatePackages(List<PackageBean> list) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.commit_alyout;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.healthtest_date:
                startPickDate();
                break;
//                            case R.id.order_button:
//                                try {
//                                    if(!setTime())
//                                        return;
//
//                                } catch (ParseException e) {
//                                    e.printStackTrace();
//                                }
//                                if(Contract.IsLogin.equals(Contract.Login))//判断是否有登录
//                                {
//                                    ReserveNow();
//                                }else
//                                {
//                                    gotToLogin();
//                                }
//                                break;
            default:
                break;
        }
    }

    public void startPickDate() {
        Calendar c = Calendar.getInstance();
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(ReserveNowActivity.this,
                // 绑定监听器
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        mDate.setText(year + "-" + (monthOfYear + 1)
                                + "-" + dayOfMonth);
                    }
                }
                // 设置初始日期
                , c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
                .get(Calendar.DAY_OF_MONTH)).show();

    }

    public boolean setTime() throws ParseException {
        if (mDate.getText().toString().equals("")) {
            SnackBarUtil.ShowSnackBar(mLinearLayout, "请输入日期", "OK!");
            return false;
        }
        switch (mPosition) {
            case 0:
                mStartTime = mDateFormat.parse(mDate.getText().toString() + " 9:00:00");
                mEndTime = mDateFormat.parse(mDate.getText().toString() + " 10:00:00");
                break;
            case 1:
                mStartTime = mDateFormat.parse(mDate.getText().toString() + " 10:00:00");
                mEndTime = mDateFormat.parse(mDate.getText().toString() + " 11:00:00");
                break;
            case 2:
                mStartTime = mDateFormat.parse(mDate.getText().toString() + " 15:00:00");
                mEndTime = mDateFormat.parse(mDate.getText().toString() + " 16:00:00");
                break;
            case 3:
                mStartTime = mDateFormat.parse(mDate.getText().toString() + " 16:00:00");
                mEndTime = mDateFormat.parse(mDate.getText().toString() + " 17:00:00");
                break;
            default:
                break;
        }
        return true;
    }

    public void ReserveNow() throws ParseException {
        mPresenter.ReserveNow(mStartTime.getTime(), mEndTime.getTime(), mEditName.getText()
                .toString(), mEditPhoneNumber.getText().toString(), mPackageBean.getId());
        loading();
    }

    public void gotToLogin() {
        DialogUtil.getLoginDialog(this).show();
    }

    public void ReserveSuccess() {
        pDialog.dismiss();
        pDialog = new SweetAlertDialog(ReserveNowActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("预约成功！")
                .setConfirmText("我知道了")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        pDialog.dismiss();
                        finish();
                    }
                });
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public void loading() {
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
    }

}
