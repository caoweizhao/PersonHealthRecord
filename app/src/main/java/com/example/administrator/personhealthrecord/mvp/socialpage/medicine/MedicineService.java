package com.example.administrator.personhealthrecord.mvp.socialpage.medicine;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017-7-19.
 */

public interface MedicineService {

    @GET("medicine/medicine_list")
    Observable<ResponseBody> getMedicineInfos();
}
