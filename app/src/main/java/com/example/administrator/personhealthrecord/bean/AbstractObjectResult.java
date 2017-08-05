package com.example.administrator.personhealthrecord.bean;

import java.util.List;

/**
 * Created by andy on 2017/8/3.
 */

public class AbstractObjectResult <T>{
    public long timestamp;
    public String status;
    public String message;
    public T object;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
