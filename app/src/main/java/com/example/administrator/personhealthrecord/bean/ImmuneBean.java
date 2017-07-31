package com.example.administrator.personhealthrecord.bean;

import android.os.Parcel;
import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2017-7-31.
 */

public class ImmuneBean implements AbstractItem {

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getSummary() {
        return null;
    }

    @Override
    public String getImageUrl() {
        return null;
    }

    @Override
    public String getdate() {
        return null;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public ImmuneBean() {
    }

    protected ImmuneBean(Parcel in) {
    }

    public static final Creator<ImmuneBean> CREATOR = new Creator<ImmuneBean>() {
        @Override
        public ImmuneBean createFromParcel(Parcel source) {
            return new ImmuneBean(source);
        }

        @Override
        public ImmuneBean[] newArray(int size) {
            return new ImmuneBean[size];
        }
    };
}
