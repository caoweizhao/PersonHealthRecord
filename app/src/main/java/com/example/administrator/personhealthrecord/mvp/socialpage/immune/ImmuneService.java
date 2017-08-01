package com.example.administrator.personhealthrecord.mvp.socialpage.immune;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017-7-26.
 */

public interface ImmuneService {

    @GET("immune/immune_list")
    Observable<ResponseBody> getImmunesInfo();
}
