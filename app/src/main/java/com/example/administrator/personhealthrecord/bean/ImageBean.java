package com.example.administrator.personhealthrecord.bean;

/**
 * Created by andy on 2017/8/2.
 */

public class ImageBean {
//    "id": 4,
//            "imageUrl": "advertisement_4.png",
//            "advertiseUrl": "http://www.kangmei.com.cn"
    public int id;
    public String imageUrl;
    public String advertiseUrl;

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
