package com.example.administrator.personhealthrecord.bean;

import java.util.List;

/**
 * Created by andy on 2017/8/21.
 */

public class NewHealthyNewsBean {
//      "object": {
//        "content": [
//        {
//            "id": 13,
//                "category": "",
//                "content": "",
//                "imageUrl": "information_6.png",
//                "summary": "",
//                "time": 1500777144000,
//                "title": ""
//        }
//    ],
//        "last": true,
//                "totalPages": 3,
//                "totalElements": 21,
//                "number": 2,
//                "size": 10,
//                "numberOfElements": 1,
//                "sort": null,
//                "first": false
//    },
    private List<HealthyNewBean> content;
    private boolean last;
    private boolean first;
    private int totalPages;
    private int number;
    private int size;
    private int numberOfElements;
    private String sort;
    private int totalElements;

    public List<HealthyNewBean> getContent() {
        return content;
    }

    public void setContent(List<HealthyNewBean> content) {
        this.content = content;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }
}
