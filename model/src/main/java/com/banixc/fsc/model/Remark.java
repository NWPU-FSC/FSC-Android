package com.banixc.fsc.model;

/**
 * Created by Lenovo on 2016/8/21.
 */
public class Remark extends Result{


    /**
     * content : 你是个好学生
     * time : 2016-08-22 16:04:43
     * teacher_name : 张三
     */

    private String content;
    private String time;
    private String teacher_name;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }
}

