package com.example.administrator.personhealthrecord.bean;

import android.os.Parcel;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017-7-20.
 */

public class CheckBean implements AbstractItem{
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("summary")
    private String summary;
    @SerializedName("content")
    private String content;
    @SerializedName("category")
    private String category;
    @SerializedName("time")
    private int reverations;//预约人数
    @SerializedName("imageUrl")
    private String imageUrl;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setReverations(int reverations) {
        this.reverations = reverations;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override

    public String getTitle() {
        return title;
    }

    @Override
    public String getSummary() {
        return summary;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String getdate() {
        return "预约人数"+reverations;
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
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.summary);
        dest.writeString(this.content);
        dest.writeString(this.category);
        dest.writeInt(this.reverations);
        dest.writeString(this.imageUrl);
    }

    public CheckBean() {
    }

    protected CheckBean(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.summary = in.readString();
        this.content = in.readString();
        this.category = in.readString();
        this.reverations = in.readInt();
        this.imageUrl = in.readString();
    }

    public static final Creator<CheckBean> CREATOR = new Creator<CheckBean>() {
        @Override
        public CheckBean createFromParcel(Parcel source) {
            return new CheckBean(source);
        }

        @Override
        public CheckBean[] newArray(int size) {
            return new CheckBean[size];
        }
    };
}
