package com.example.administrator.personhealthrecord.bean;

import java.util.List;

/**
 * Created by andy on 2017/8/1.
 */

public class ResultUtilOfHealthyOrderBean {
    public long timestamp;
    public String status;

    public String message;
    public List<ReserveBean> collection;

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

    public List<ReserveBean> getCollection() {
        return collection;
    }

    public void setCollection(List<ReserveBean> collection) {
        this.collection = collection;
    }
}
