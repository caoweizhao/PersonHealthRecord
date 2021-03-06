package com.example.administrator.personhealthrecord.contract;

import android.support.annotation.IntDef;

/**
 * Created by Administrator on 2017-7-19.
 */

public class Contract {

    public static boolean IsTest = false;
    public static final String Login = "Login";
    public static final String UnLogin = "UnLogin";
    public static String IsLogin = UnLogin;

    /**
     * 点击自助挂号时传入当前地址的key
     */
    public static final String ADDRESS_KEY = "ADDRESS_KEY";
    public static final String HOSPITAL_KEY = "HOSPITAL_KEY";
    public static final String EXPERT_KEY = "EXPERT_KEY";
    public static final String ADURL_KEY = "AD_KEY";


    public static String cookie = "";
    //远程URL
    public static final String Remote_URL = "http://106.14.136.52:8080/";
    //本地URL
    public static final String Local_URL = "http://192.168.13.70:8080/";
    //URL
    public static String BASE_URL = IsTest?Local_URL:Remote_URL;

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

    public static final String PackageImageBase = BASE_URL + "medical_package/getImage/";
    public static final String HealthyNewsImageUrl = BASE_URL + "health_info/getImage/";


    public static final String ReserveOrderHealthyCheckImageUrl = BASE_URL + "medical_order/getImage/";

    public static final String CheckPageAdvertisementImageUrl = BASE_URL + "/advertisement/getImage/";
    public static final String AppointmentImageUrl = BASE_URL + "rro/getImage/";
    public static boolean IS_DISCOUNT = false;

    private static String getUrl() {
        if (IsTest)
            return Local_URL;
        else
            return Remote_URL;
    }
}
