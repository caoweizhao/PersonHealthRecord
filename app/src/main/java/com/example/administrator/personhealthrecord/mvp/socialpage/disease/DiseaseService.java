package com.example.administrator.personhealthrecord.mvp.socialpage.disease;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017-7-25.
 */

public interface DiseaseService {

    @GET("slow_disease/disease_list")
    Observable<ResponseBody> initDiseaseInfo();

}
