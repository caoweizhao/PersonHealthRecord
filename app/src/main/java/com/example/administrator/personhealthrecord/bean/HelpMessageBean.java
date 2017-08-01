package com.example.administrator.personhealthrecord.bean;

/**
 * Created by andy on 2017/8/1.
 */

public class HelpMessageBean {
    public int id;
    public String question;
    public String anwser;
    public long time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnwser() {
        return anwser;
    }

    public void setAnwser(String anwser) {
        this.anwser = anwser;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
