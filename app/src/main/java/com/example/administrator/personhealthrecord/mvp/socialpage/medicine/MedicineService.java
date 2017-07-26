package com.example.administrator.personhealthrecord.mvp.socialpage.medicine;

import com.example.administrator.personhealthrecord.bean.MedicineBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017-7-19.
 */

public interface MedicineService {

    @GET("medicine/medicine_list")
    Observable<List<MedicineBean>> getMedicineInfos();
}
