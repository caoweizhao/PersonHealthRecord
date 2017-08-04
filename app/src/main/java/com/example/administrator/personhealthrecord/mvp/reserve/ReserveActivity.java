package com.example.administrator.personhealthrecord.mvp.reserve;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.activity.HospitalPackageDetailActivity;
import com.example.administrator.personhealthrecord.adapter.HospitalAdapter;
import com.example.administrator.personhealthrecord.adapter.PackageInfoAdapter;
import com.example.administrator.personhealthrecord.bean.HospitalBean;
import com.example.administrator.personhealthrecord.bean.PackageBean;
import com.example.administrator.personhealthrecord.contract.Contract;
import com.example.administrator.personhealthrecord.util.AnimateUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ReserveActivity extends IReserveView {
    private boolean IS_DISCOUNT=false;
    private String IsOnHospital="IsOnHospital";
    private String IsOnPackage="IsOnPackage";
    private String status;
    private List<PackageBean> pacakges;
    private HospitalAdapter adapter;
    private List<HospitalBean> hospitals;
    private PackageInfoAdapter packageAdapter;
    @BindView(R.id.health_check_itme_list)
    RecyclerView hospitalsRecycleview;
    @BindView(R.id.health_check_package_list)
    RecyclerView packageZRecycleview;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_reserve;
    }

    @Override
    public IResrevePresenter createPresenter() {
        return new ReservePresenterImpl();
    }

    @Override
    protected void initData() {
        Intent intent=getIntent();
        IS_DISCOUNT=intent.getBooleanExtra("IS_DISCOUNT",false);
        Contract.IS_DISCOUNT=IS_DISCOUNT;
        super.initData();
        status=IsOnHospital;
        initToolbar("体检机构",true,null);
        hospitals=new ArrayList<>();
        adapter=new HospitalAdapter(R.layout.hospital_item,hospitals);
        hospitalsRecycleview.setLayoutManager(new LinearLayoutManager(this));
        hospitalsRecycleview.setAdapter(adapter);
        getPackageHospitals();
        pacakges=new ArrayList<>();
        packageAdapter=new PackageInfoAdapter(R.layout.health_check_package_item,pacakges);
        packageZRecycleview.setLayoutManager(new LinearLayoutManager(this));
        packageZRecycleview.setAdapter(packageAdapter);

    }

    @Override
    protected void initEvents() {
        super.initEvents();
       adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
           @Override
           public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
               AnimateUtil.createCircularReveal(view);
              HospitalBean bean=(HospitalBean) adapter.getData().get(position);
               Log.d(TAG, "onItemChildClick: "+position);
               getPackage(bean.getId());
           }
       });
        packageAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                AnimateUtil.createCircularReveal(view);
                Log.d(TAG, "onItemChildClick: aaaaaaaaaaa");
                Intent intent=new Intent(ReserveActivity.this, HospitalPackageDetailActivity.class);
                intent.putExtra("packagebean",(PackageBean)adapter.getData().get(position));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(ReserveActivity.this,
                            Pair.create(view.findViewById(R.id.health_check_item__img), "image"),
                            Pair.create(view.findViewById(R.id.health_check_item__title), "title"))
                            .toBundle());
                }else
                {
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void getPackageHospitals() {
        mPresenter.getPackageHospitals();
    }

    @Override
    public void updataHostpitals(List<HospitalBean> list) {
        adapter.addData(list);
    }

    @Override
    public void getPackage(int id) {
        mPresenter.getPackage(id);
    }

    @Override
    public void updatePackgets(List<PackageBean> list) {
        status=IsOnPackage;
        initToolbar("体检套餐",true,null);
        hospitalsRecycleview.setVisibility(View.GONE);
        packageZRecycleview.setVisibility(View.VISIBLE);
        Log.d(TAG, "updatePackgets: "+pacakges.size());
         int i;
                 for(i=0;i<pacakges.size();i++)
                 {
                     pacakges.remove(0);
                 }
        packageAdapter.notifyDataSetChanged();
        packageAdapter.addData(list);
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: ");
        if(status.equals(IsOnPackage))
        {
            hospitalsRecycleview.setVisibility(View.VISIBLE);
            packageZRecycleview.setVisibility(View.GONE);
            status=IsOnHospital;
            initToolbar("体检机构",true,null);
        }else
        {
            super.onBackPressed();
        }

    }
}
