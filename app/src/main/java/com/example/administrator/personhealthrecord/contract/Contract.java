package com.example.administrator.personhealthrecord.contract;

import android.support.annotation.IntDef;

/**
 * Created by Administrator on 2017-7-19.
 */

public class Contract {
    public static final String BASE_URL = "http://192.168.13.39:8080/";
    /**
     * 心血管内科
     */
    public static final int TYPE_CARDIOLOGY = 0;
    /**
     * 内科
     */
    public static final int TYPE_INTERNAL_MEDICINE = 1;
    /**
     * 外科
     */
    public static final int TYPE_SURGICAL = 2;
    /**
     * 皮肤科
     */
    public static final int TYPE_DERMATOLOGY = 3;


    @IntDef(value = {TYPE_CARDIOLOGY, TYPE_DERMATOLOGY, TYPE_INTERNAL_MEDICINE, TYPE_SURGICAL})
    public @interface ExpertType {
    }
    //图片的baseURl
    public static final String ImageUrl=BASE_URL+"information/getImage/";
}
