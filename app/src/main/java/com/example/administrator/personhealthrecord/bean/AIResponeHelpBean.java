package com.example.administrator.personhealthrecord.bean;

/**
 * Created by andy on 2017/8/1.
 */

public class AIResponeHelpBean {
//     "message": "AI消息返回成功",
//             "object": {
//        "id": 1,
//                "question": "帮助",
//                "anwser": "1、帮助：显示可回答的问题列表\r\n2、预约体检 \r\n3、预约挂号\r\n4、我在其他医院的病历能够上传吗？\r\n5、社区功能 \r\n6、健康指数评估 （评估）（健康指数）\r\n7、什么是PHR\r\n8、私人医生 \r\n9、如何注册\r\n10、如何登录\r\n11、密码忘记了怎么办\r\n12、怎样修改密码",
//                "time": 1501483502000
//    },
//            "timestamp": 1501567588363,
//            "status": "success"

    public String message;
    public long timestamp;
    public String  status;
    public HelpMessageBean object;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public HelpMessageBean getObject() {
        return object;
    }

    public void setObject(HelpMessageBean object) {
        this.object = object;
    }

}
