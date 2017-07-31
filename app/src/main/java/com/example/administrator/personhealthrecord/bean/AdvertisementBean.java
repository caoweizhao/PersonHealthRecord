package com.example.administrator.personhealthrecord.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017-7-30.
 */

public class AdvertisementBean {

    /**
     * id : 1
     * imageUrl : advertisement_1.png
     * advertiseUrl : http://www.doctorjob.com.cn/logo/speciality/index/226625/index.jsp
     */

    @SerializedName("id")
    private int id;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("advertiseUrl")
    private String advertiseUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAdvertiseUrl() {
        return advertiseUrl;
    }

    public void setAdvertiseUrl(String advertiseUrl) {
        this.advertiseUrl = advertiseUrl;
    }
}
