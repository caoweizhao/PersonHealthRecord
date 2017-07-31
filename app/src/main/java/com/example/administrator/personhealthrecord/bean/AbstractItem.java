package com.example.administrator.personhealthrecord.bean;

import android.os.Parcelable;

/**
 * Created by Administrator on 2017-7-19.
 */

public interface AbstractItem extends Comparable, Parcelable {
    public abstract String getTitle();

    public abstract String getSummary();

    public abstract String getImageUrl();

    public abstract String getdate();

}
