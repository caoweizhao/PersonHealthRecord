package com.example.administrator.personhealthrecord.mvp.socialpage.disease;

import com.example.administrator.personhealthrecord.bean.DiseaseBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017-7-25.
 */

public interface DiseaseService {

    @GET("slow_disease/disease_list")
    Observable<List<DiseaseBean>> fetchDiseaseList();

    @GET("")
    Observable<List<DiseaseBean>> loadMoreDisease(@Query("date")String date);
}
