package com.example.administrator.personhealthrecord.mvp.reserve;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.activity.HospitalPackageDetailActivity;
import com.example.administrator.personhealthrecord.adapter.HospitalAdapter;
import com.example.administrator.personhealthrecord.adapter.PackageInfoAdapter;
import com.example.administrator.personhealthrecord.bean.HospitalBean;
import com.example.administrator.personhealthrecord.bean.PackageBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ReserveActivity extends IReserveView {
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
        super.initData();
        status=IsOnHospital;
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
              HospitalBean bean=(HospitalBean) adapter.getData().get(position);
               Log.d(TAG, "onItemChildClick: "+position);
               getPackage(bean.getId());
           }
       });
        packageAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d(TAG, "onItemChildClick: aaaaaaaaaaa");
                Intent intent=new Intent(ReserveActivity.this, HospitalPackageDetailActivity.class);
                intent.putExtra("packagebean",(PackageBean)adapter.getData().get(position));
                startActivity(intent);
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
        }else
        {
            super.onBackPressed();
        }

    }
}
