package com.banixc.fsc.model;

import java.io.Serializable;




public class Homework extends Result implements Serializable {


    /**
     * title : 语文作业
     * content : 1
     * date : 2016-08-18
     * send_time : 2016-08-18 11:06:24
     * sender_teacher : 李四
     * course_name : 语文
     */

    private String title;
    private String content;
    private String date;
    private String send_time;
    private String sender_teacher;
    private String course_name;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }

    public String getSender_teacher() {
        return sender_teacher;
    }

    public void setSender_teacher(String sender_teacher) {
        this.sender_teacher = sender_teacher;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
}
