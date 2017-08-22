package com.example.administrator.personhealthrecord.mvp.reserve.api;

import com.example.administrator.personhealthrecord.bean.ResultUitlOfReserve;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfHospitalList;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfPackageBean;

import java.sql.Timestamp;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by andy on 2017/7/27.
 */

public interface HealthReserveService {
    @GET("hospital/package_hospital_list")
    Observable<ResultUtilOfHospitalList> getOwnPackageHospitalList();

    @GET("medical_package/package_list/{hospital}")
    Observable<ResultUtilOfPackageBean> getHospitalPackage(@Path("hospital") int id);

    @FormUrlEncoded
    @POST("/medical_order/new_medical_order")
    Observable<ResultUitlOfReserve> ReserveNow(@Header("Cookie") String cookie, @Field("startTime") Timestamp startTime, @Field("endTime") Timestamp endTime, @Field("name") String name, @Field("phoneNumber") String phoneNumber, @Field("medical_package_id") int id);

    @GET("medical_package/package_favourable_hospital_list")
    Observable<ResultUtilOfHospitalList> getDiscountPackageHospitalList();

    @GET("medical_package/favourable_package_list_by_hospital_id/{hospital_id}")
    Observable<ResultUtilOfPackageBean> getHospitalDiscountPackage(@Path("hospital_id") int id);
}
