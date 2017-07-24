package com.example.administrator.personhealthrecord.mvp.socialpage.medical.api;

import com.example.administrator.personhealthrecord.bean.MedicineInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017-7-19.
 */

public interface MedicalService {

    @GET("")
    Observable<List<MedicineInfo>> getMedicineInfos();
}
