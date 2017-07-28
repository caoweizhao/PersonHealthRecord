package com.example.administrator.personhealthrecord.mvp.reserve.api;

import com.example.administrator.personhealthrecord.bean.ResultUtilOfPackageBean;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfHospitalList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by andy on 2017/7/27.
 */

public interface HealthReserveService {
    @GET("hospital/package_hospital_list")
    Observable<ResultUtilOfHospitalList> getOwnPackageHostpitalList();

    @GET("medical_package/package_list/{hospital}")
    Observable<ResultUtilOfPackageBean> getHospitalPacakge(@Path("hospital") int id);
}
