package com.example.administrator.personhealthrecord.bean;

import android.os.Parcelable;

/**
 * Created by Administrator on 2017-7-19.
 */

public interface AbstractItem extends Comparable, Parcelable {
    String getTitle();

    String getSummary();

    String getImageUrl();

    String getDate();

}
