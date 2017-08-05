package com.example.administrator.personhealthrecord.contract;

import android.support.annotation.IntDef;

/**
 * Created by Administrator on 2017-7-19.
 */

public class Contract {
    public static final String Login = "Login";
    public static final String Unlogin = "Unlogin";
    public static String IsLogin = Unlogin;

    /**
     * 点击自助挂号时传入当前地址的key
     */
    public static final String ADDRESS_KEY = "ADDRESS_KEY";
    public static final String HOSPITAL_KEY = "HOSPITAL_KEY";
    public static final String EXPERT_KEY = "EXPERT_KEY";
    public static final String ADURL_KEY = "AD_KEY";


    public static String cookie = "";
    public static final String BASE_URL = "http://192.168.13.39:8080/";

    //专家推荐类型
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

    /**
     * SocialPage中四个Fragment的颜色
     */
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
    public static final String HospitalBase = BASE_URL + "hospital/getImage/";
    public static final String DiseaseBase = BASE_URL + "slow_disease/getImage/";
    public static final String MedicalBase = BASE_URL + "medicine/getImage/";
    public static final String ImmuneBase = BASE_URL + "plan_immunity/getImage/";
    public static final String DoctorBase = BASE_URL + "doctor/getImage/";
    public static final String AdvertisementBase = BASE_URL + "advertisement/getImage/";
    public static final String UserInfoBase = BASE_URL + "user_info/getImage/";

    public static final String PackageImageBase = "http://192.168.13.39:8080/medical_package/getImage/";
    public static final String HealthyNewsImageUrl = BASE_URL + "health_info/getImage/";


    public static final String ReserVeOrderHealthyCheckImageUrl = BASE_URL + "rro/getImage/";

    public static final String CheckPageAdvertismentImageUrl = BASE_URL + "/advertisement/getImage/";
}
