package com.example.administrator.personhealthrecord.bean;

import com.google.gson.annotations.SerializedName;

import java.security.Timestamp;

/**
 * Created by andy on 2017/7/24.
 */

public class LoginBean {
    @SerializedName("message")
    private String message;
    @SerializedName("object")

    private Object object;
    @SerializedName("timestamp")
    private long timestamp;
    @SerializedName("status")
    private String status;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
