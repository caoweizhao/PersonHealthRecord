package com.example.administrator.personhealthrecord.mvp.reserveorder.api;

import com.example.administrator.personhealthrecord.bean.AbstractObjectResult;
import com.example.administrator.personhealthrecord.bean.AppointmentBean;
import com.example.administrator.personhealthrecord.bean.ReserOrderBean;
import com.example.administrator.personhealthrecord.bean.ResultUitlOfReserve;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfHealthyOrderBean;
import com.example.administrator.personhealthrecord.bean.ResultUtilOfPackageBean;

import java.sql.Timestamp;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by andy on 2017/7/31.
 */

public interface ReserveOrderService {
    @GET("medical_order/medical_order_list")
    Observable<ResultUtilOfHealthyOrderBean<ReserOrderBean>> getHealthyCheckOrderList(@Header("Cookie")String cookie);
    @GET("rro/order_list")
    Observable<ResultUtilOfHealthyOrderBean<AppointmentBean>> getAppoitment(@Header("Cookie")String cookie);
    //取消挂号
    @FormUrlEncoded
    @PUT("rro/cancel_order")
    Observable<AbstractObjectResult<AppointmentBean>> cancleAppointMnet(@Header("Cookie")String cookie,@Field("id")int id);

    @FormUrlEncoded
    @PUT("medical_order/cancel_medical_order")
    Observable<AbstractObjectResult<ReserOrderBean>> cancleHealthyCheck(@Header("Cookie")String cookie,@Field("id")int id);
}
