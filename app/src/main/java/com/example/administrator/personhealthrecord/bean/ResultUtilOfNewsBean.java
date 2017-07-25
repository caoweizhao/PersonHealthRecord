package com.example.administrator.personhealthrecord.bean;

import java.util.List;

/**
 * Created by andy on 2017/7/25.
 */

public class ResultUtilOfNewsBean {
//    {"timestamp":1500945112881,"status":"success","message":"列表返回成功",
//            "collection":[{"id":24,"category":"新闻","content":"印度和中国一直以来的关系有点微妙。" +
//            "对于中国，印度一直以来都是不服气的，甚至可以说是蔑视的，甚至一直有人叫嚣印度会赶超中国。就连中国最大的工程，" +
//            "中国的新丝绸之路印度也没有参与，可见印度是不么不服气中国了。\n印度阿三厕所很少是公认的，大家伙随地大小便也不雅观，" +
//            "有着闲心跑来耍无赖，倒不如多修厕所解决大家伙的如厕难题。","imageUrl":"information_3.png","summary":"印度和中国的差距，" +
//            "不仅仅是相差着1.5亿个空调那么简单！","time":1500945001000,"title":"印度和中国的差距"},
//        {"id":25,"category":"新闻","content":"自从加加林第一次走进太空，在此后的几十年中，人类开启了走向太空的路途。" +
//                "现在，人类已经可以修建空间站，在太空生活很长一段时间。 美国宇航局宇航员斯科特·凯利（ScottKelly）曾连续在太空中停" +
//                "留了340天，接近一年，他在期间参加了3次太空行走，围绕地球轨道共飞行了5440圈，在他返回地球之后，他向世人描述了在空" +
//                "间站生活一年的奇幻经历。","imageUrl":"information_6.png","summary":"美国宇航员爆料：从太空看到的地球就像是患了" +
//                "重病！","time":1500945069000,"title":"地球在哀嚎"}]}
    public long timestamp;
    public String status;

    public String message;
    public List<NewsBean> collection;


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

    public List<NewsBean> getCollection() {
        return collection;
    }

    public void setCollection(List<NewsBean> collection) {
        this.collection = collection;
    }
}
