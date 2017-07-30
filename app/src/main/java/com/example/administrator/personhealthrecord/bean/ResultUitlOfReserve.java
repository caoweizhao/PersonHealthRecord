package com.example.administrator.personhealthrecord.bean;

/**
 * Created by andy on 2017/7/30.
 */

public class ResultUitlOfReserve {
    public long timestamp;
    public String status;

    public String message;
    public ReserveBean object;

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

    public ReserveBean getObject() {
        return object;
    }

    public void setObject(ReserveBean object) {
        this.object = object;
    }
}
