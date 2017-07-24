package com.example.administrator.personhealthrecord.bean;

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
}
