package com.example.administrator.personhealthrecord.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.example.administrator.personhealthrecord.R;

public class MapAcitvity extends AppCompatActivity implements LocationSource,AMapLocationListener {

    private MapView mapView;
    private AMap map;

    //定位功能
    private static final String TAG="MainActivity";
    private LocationSource.OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_acitvity);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                //申请WRITE_EXTERNAL_STORAGE权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},1);//自定义的code
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                //申请WRITE_EXTERNAL_STORAGE权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},2);//自定义的code
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                //申请WRITE_EXTERNAL_STORAGE权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},3);//自定义的code
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                //申请WRITE_EXTERNAL_STORAGE权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},4);//自定义的code
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED) {
                //申请WRITE_EXTERNAL_STORAGE权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},5);//自定义的code
            }

        }
        mapView = (MapView)findViewById(R.id.my_map_view);
        mapView.onCreate(savedInstanceState);
        map = mapView.getMap();

        //修改地图的中心点位置
        CameraPosition cp = map.getCameraPosition();
        CameraPosition cpNew = CameraPosition.fromLatLngZoom(new LatLng(31.22, 121.48), cp.zoom);
        CameraUpdate cu = CameraUpdateFactory.newCameraPosition(cpNew);
        map.moveCamera(cu);

        //初始化定位服务
        initLocationService();



    }

    //初始化定位服务
    private void initLocationService() {
        if (map != null) {
            MyLocationStyle locationStyle = new MyLocationStyle();
//            locationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker));
            locationStyle.strokeColor(Color.BLACK);
            locationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));
            locationStyle.strokeWidth(1.0f);
            map.setMyLocationStyle(locationStyle);
            map.setLocationSource(this);
            map.getUiSettings().setMyLocationButtonEnabled(true);
            map.setMyLocationEnabled(true);
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        mapView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        mapView.onPause();
        deactivate();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        mapView.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onLocationChanged(AMapLocation amaplocation) {
        // TODO Auto-generated method stub
        if (amaplocation != null && mListener != null) {
            if (amaplocation != null && amaplocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amaplocation);
                Log.d(TAG, "onLocationChanged: "+amaplocation.getAddress());
            }
            else {
                String errText = "onLocationChanged"+"failed to locate," + amaplocation.getErrorCode()+ ": "
                        + amaplocation.getErrorInfo();
                Log.e("error",errText);
            }

        }
    }


    @Override
    public void activate(OnLocationChangedListener listener) {
        // TODO Auto-generated method stub
        mListener = listener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(getApplicationContext());
            mLocationOption = new AMapLocationClientOption();
            mlocationClient.setLocationListener(this);
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            mlocationClient.setLocationOption(mLocationOption);
            mlocationClient.startLocation();
        }
    }


    @Override
    public void deactivate() {
        // TODO Auto-generated method stub
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
        mLocationOption = null;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
