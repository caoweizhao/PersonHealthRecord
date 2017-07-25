package com.example.administrator.personhealthrecord.bean;

import java.util.List;

/**
 * Created by andy on 2017/7/25.
 */

public class ResultUtilOfHospitalList {
    public long timestamp;
    public String status;

    public String message;
    public List<HospitalBean> collection;

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

    public List<HospitalBean> getCollection() {
        return collection;
    }

    public void setCollection(List<HospitalBean> collection) {
        this.collection = collection;
    }
}
