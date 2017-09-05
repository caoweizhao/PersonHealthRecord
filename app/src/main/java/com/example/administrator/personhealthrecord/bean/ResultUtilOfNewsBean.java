package com.example.administrator.personhealthrecord.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by andy on 2017/7/25.
 */

public class ResultUtilOfNewsBean {

    /**
     * message : 列表返回成功
     * object : {"content":[{"id":13,"category":"","content":"","imageUrl":"information_6.png","summary":"","time":1500777144000,"title":""}],"last":true,"totalPages":3,"totalElements":21,"number":2,"size":10,"numberOfElements":1,"sort":null,"first":false}
     * timestamp : 1503284007547
     * status : success
     */

    @SerializedName("message")
    private String message;
    @SerializedName("object")
    private ObjectBean object;
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

    public ObjectBean getObject() {
        return object;
    }

    public void setObject(ObjectBean object) {
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

    public static class ObjectBean {
        /**
         * content : [{"id":13,"category":"","content":"","imageUrl":"information_6.png","summary":"","time":1500777144000,"title":""}]
         * last : true
         * totalPages : 3
         * totalElements : 21
         * number : 2
         * size : 10
         * numberOfElements : 1
         * sort : null
         * first : false
         */

        @SerializedName("last")
        private boolean last;
        @SerializedName("totalPages")
        private int totalPages;
        @SerializedName("totalElements")
        private int totalElements;
        @SerializedName("number")
        private int number;
        @SerializedName("size")
        private int size;
        @SerializedName("numberOfElements")
        private int numberOfElements;
        @SerializedName("sort")
        private Object sort;
        @SerializedName("first")
        private boolean first;
        @SerializedName("content")
        private List<NewsBean> content;

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public Object getSort() {
            return sort;
        }

        public void setSort(Object sort) {
            this.sort = sort;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public List<NewsBean> getContent() {
            return content;
        }

        public void setContent(List<NewsBean> content) {
            this.content = content;
        }

    }
}
