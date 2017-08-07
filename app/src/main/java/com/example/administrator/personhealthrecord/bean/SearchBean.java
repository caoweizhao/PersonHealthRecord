package com.example.administrator.personhealthrecord.bean;

/**
 * Created by Administrator on 2017-8-7.
 */

public interface SearchBean {

    int TYPE_DOCTOR = 1;
    int TYPE_HOSPITAL = 2;
    int TYPE_MEDICINE = 3;

    String getName();
    void setName(String name);

    String getSummary();

    int getType();

    String getImageUrl();
}
