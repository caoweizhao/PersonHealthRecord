package com.example.administrator.personhealthrecord.activity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
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
import com.example.administrator.personhealthrecord.mvp.registandlogin.LoginActivity;
import com.example.administrator.personhealthrecord.mvp.reserve.IReserveView;
import com.example.administrator.personhealthrecord.mvp.reserve.IResrevePresenter;
import com.example.administrator.personhealthrecord.mvp.reserve.ReserveActivity;
import com.example.administrator.personhealthrecord.mvp.reserve.ReservePresenterImpl;
import com.example.administrator.personhealthrecord.util.SnackBarUitl;
import com.example.administrator.personhealthrecord.util.ToastUitl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by andy on 2017/7/28.
 */

public class ReserveNowActivity extends IReserveView implements View.OnClickListener{
    @BindView(R.id.healthtest_pic)
    ImageView imageView;
    @BindView(R.id.health_check_item_linerlayout)
    LinearLayout linearLayout;
    @BindView(R.id.healthtest_name)
    TextView name;
    @BindView(R.id.healthtest_details)
    TextView summary;
    @BindView(R.id.healthtest_price)
    TextView price;
    @BindView(R.id.healthtest_count)
    TextView count;
    @BindView(R.id.healthtest_date)
    TextView date;
    @BindView(R.id.input_date)
    ImageView getDate;
    @BindView(R.id.input_Phone)
    EditText editphoneNumber;
    @BindView(R.id.input_name)
    EditText editname;
    @BindView(R.id.health_check_select_time)
    Spinner spinner;
    @BindView(R.id.order_button)
    Button reserveNow;
    private Date stratTime;
    private Date endTime;
    private DateFormat dateFormat;
    private int Position;
    private SweetAlertDialog pDialog;
private PackageBean bean;
    @Override
    protected void initData() {
        super.initData();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        bean=getIntent().getParcelableExtra("package");
        Log.d(TAG, "initData: "+bean.getName());
        summary.setText(bean.getSummary());
        name.setText(bean.getName());
        count.setText("X1");
        price.setText("￥"+bean.getPackagePrice());
        Glide.with(this).load(Contract.PackageImageBase+bean.getImageUrl())
                .into(imageView);
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        getDate.setOnClickListener(this);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               Position=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        reserveNow.setOnClickListener(this);
    }

    @Override
    public IResrevePresenter createPresenter() {
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
    public void updataHostpitals(List<HospitalBean> list) {

    }

    @Override
    public void getPackage(int id) {

    }

    @Override
    public void updatePackgets(List<PackageBean> list) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.commit_layout;
    }

    @Override
    public void onClick(View v) {
                switch (v.getId())
                        {
                            case R.id.input_date:
                                startPickdate();
                                break;
                            case R.id.order_button:
                                try {
                                    if(!setTime())
                                        return;

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                if(Contract.IsLogin.equals(Contract.Login))//判断是否有登录
                                {
                                    ReserveNow();
                                }else
                                {
                                    gotToLogin();
                                }
                                break;
                            default:
                                break;
                        }
    }
    public void startPickdate()
    {
        Calendar c = Calendar.getInstance();
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(ReserveNowActivity.this,
                // 绑定监听器
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        date.setText(  year + "-" + monthOfYear
                                + "-" + dayOfMonth );
                    }
                }
                // 设置初始日期
                , c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
                .get(Calendar.DAY_OF_MONTH)).show();

    }
    public boolean setTime() throws ParseException
    {
        if(date.getText().toString().equals(""))
        {
            SnackBarUitl.ShowSnackBar(linearLayout,"请输入日期","OK!");
            return false;
        }
        switch(Position)
            {
                case 0:
                    stratTime = dateFormat.parse(date.getText().toString()+" 9:00:00");
                    endTime=dateFormat.parse(date.getText().toString()+" 10:00:00");
                    break;
                case 1:
                    stratTime = dateFormat.parse(date.getText().toString()+" 10:00:00");
                    endTime=dateFormat.parse(date.getText().toString()+" 11:00:00");
                    break;
                case 2:
                    stratTime = dateFormat.parse(date.getText().toString()+" 15:00:00");
                    endTime=dateFormat.parse(date.getText().toString()+" 16:00:00");
                    break;
                case 3:
                    stratTime = dateFormat.parse(date.getText().toString()+" 16:00:00");
                    endTime=dateFormat.parse(date.getText().toString()+" 17:00:00");
                    break;
                default:
                    break;
            }
        return true;
    }

    public void ReserveNow()
    {
        if(editname.getText().toString().equals("")||editphoneNumber.getText().toString().equals(""))
            SnackBarUitl.ShowSnackBar(linearLayout,"请填完个人信息","OK!");
        else
        {
            mPresenter.ReserveNow(stratTime.getTime(),endTime.getTime(),editname.getText().toString(),editphoneNumber.getText().toString(),bean.getId());
            loding();
        }
    }

    public void gotToLogin()
    {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("您还没有登录！")
                .setContentText("是否去登录界面？")
                .setCancelText("不了~")
                .setConfirmText("去登陆->")
                .showCancelButton(true)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                        Intent intent=new Intent(ReserveNowActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                })
                .show();
    }
    public void ReserveSuccess()
    {
        pDialog.getProgressHelper().setProgress(100);
    }
    public void loding()
    {
         pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
    }


}
