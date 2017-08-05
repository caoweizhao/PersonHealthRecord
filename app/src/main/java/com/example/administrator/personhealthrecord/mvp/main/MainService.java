package com.example.administrator.personhealthrecord.mvp.main;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Administrator on 2017-8-4.
 */

public interface MainService {

    @GET("user_info/search")
    Observable<ResponseBody> getSelfInfo(@Header("Cookie") String cookie);
}
