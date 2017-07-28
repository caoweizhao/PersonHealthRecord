package com.example.administrator.personhealthrecord.contract;

import android.support.annotation.IntDef;

/**
 * Created by Administrator on 2017-7-19.
 */

public class Contract {
    //public static final String BASE_URL = "http://192.168.13.39:8080/";
    public static final String BASE_URL = "http://192.168.191.1:8080/";

    /**
     * 骨科
     */
    public static final int TYPE_ORTHOPEDICS = 0;
    /**
     * 小儿外科
     */
    public static final int TYPE_PEDIATRIC_SURGERY = 1;
    /**
     * 耳鼻喉科
     */
    public static final int TYPE_ENT = 2;
    /**
     * 皮肤科
     */
    public static final int TYPE_DERMATOLOGY = 3;

    public static String[] ExpertType = new String[]{"D1", "D7", "D22", "D18"};

    public static final String[] colorsStr = new String[]{
            "#ff5d4037", "#ff00786b", "#ff455a64", "#ff0096a6"
    };
    public static final String[] colorsLighterStr = new String[]{
            "#ff785548", "#ff009587", "#ff607c8a", "#ff4ebcc7"
    };


    public static int[] colors = new int[]{0xff5d4037, 0xff00786b, 0xff455a64, 0xff0096a6};
    public static int[] colorsLighter = new int[]{0xff785548, 0xff009587, 0xff607c8a, 0xff4ebcc7};


    @IntDef(value = {TYPE_ORTHOPEDICS, TYPE_DERMATOLOGY, TYPE_PEDIATRIC_SURGERY, TYPE_ENT})
    public @interface ExpertType {
    }

    //图片的baseURl
    public static final String ImageUrl = BASE_URL + "information/getImage/";
    public static String cookie = "";
    public static final String HospitalBase = BASE_URL + "hospital/getImage/";
    public static final String DiseaseBase = BASE_URL + "slow_disease/getImage/";
    public static final String MedicalBase = BASE_URL + "medicine/getImage/";

}
