package com.example.administrator.personhealthrecord.mvp.reserve.api;

import com.example.administrator.personhealthrecord.bean.ResultUitlOfReserve;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfPackageBean;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfHospitalList;
import com.example.administrator.personhealthrecord.contract.Contract;

import java.sql.Timestamp;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by andy on 2017/7/27.
 */

public interface HealthReserveService {
    @GET("hospital/package_hospital_list")
    Observable<ResultUtilOfHospitalList> getOwnPackageHostpitalList();

    @GET("medical_package/package_list/{hospital}")
    Observable<ResultUtilOfPackageBean> getHospitalPacakge(@Path("hospital") int id);

    @FormUrlEncoded
    @POST("/medical_order/new_medical_order")
    Observable<ResultUitlOfReserve> ReserveNow(@Header("Cookie")String cookie, @Field("startTime")Timestamp startTime, @Field("endTime")Timestamp endTime, @Field("name")String name, @Field("phoneNumber")String phoneNumber,@Field("medical_package_id")int id);
}
