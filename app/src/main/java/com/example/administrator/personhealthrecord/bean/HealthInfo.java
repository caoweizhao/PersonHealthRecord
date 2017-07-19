package com.example.administrator.personhealthrecord.bean;

/**
 * Created by Administrator on 2017-7-19.
 */

public class HealthInfo extends AbstractItem{
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
    public String getDate() {
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
}
