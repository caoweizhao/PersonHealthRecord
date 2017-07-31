package com.example.administrator.personhealthrecord.bean;

import android.os.Parcel;
import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2017-7-19.
 */

public class HealthInfo implements AbstractItem{
    private String title;
    private String summary;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    @Override
    public String getImageUrl() {
        return null;
    }

    @Override
    public String getdate() {
        return null;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        dest.writeString(this.title);
        dest.writeString(this.summary);
        dest.writeString(this.content);
    }

    public HealthInfo() {
    }

    protected HealthInfo(Parcel in) {
        this.title = in.readString();
        this.summary = in.readString();
        this.content = in.readString();
    }

    public static final Creator<HealthInfo> CREATOR = new Creator<HealthInfo>() {
        @Override
        public HealthInfo createFromParcel(Parcel source) {
            return new HealthInfo(source);
        }

        @Override
        public HealthInfo[] newArray(int size) {
            return new HealthInfo[size];
        }
    };
}
