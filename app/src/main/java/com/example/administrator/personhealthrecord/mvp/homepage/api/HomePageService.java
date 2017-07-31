package com.example.administrator.personhealthrecord.mvp.homepage.api;

import com.example.administrator.personhealthrecord.bean.ResultUtilOfHospitalList;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017-7-19.
 */

public interface HomePageService {

    @GET("advertisement/ad_list")
    Observable<ResponseBody> getImagesUrl();

    @GET("doctor/find_doctor_by_dc/{departmentCode}")
    Observable<ResponseBody> getExperts(@Path("departmentCode") String departmentCode);

    @GET("hospital/hospital_list")
    Observable<ResultUtilOfHospitalList> getHospitals();
}
