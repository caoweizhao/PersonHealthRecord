package com.example.administrator.personhealthrecord.mvp.homepage.api;

import com.example.administrator.personhealthrecord.bean.ExpertBean;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfHospitalList;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017-7-19.
 */

public interface HomePageService {

    @GET("")
    Observable<Result<List<String>>> getImagesUrl();

    @GET("doctor/find_doctor_by_department_code")
    Observable<List<ExpertBean>> getExperts(@Query("departmentCode") String departmentCode);

    @GET("hospital/hospital_list")
    Observable<ResultUtilOfHospitalList> getHospitals();
}
