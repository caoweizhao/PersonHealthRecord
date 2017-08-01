package com.example.administrator.personhealthrecord.bean;

/**
 * Created by jared on 16/2/10.
 */
public class ChatMessageBean {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SEND = 1;

    private String content;
    private int type;

    public ChatMessageBean(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
