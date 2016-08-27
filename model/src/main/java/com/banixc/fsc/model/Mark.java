package com.banixc.fsc.model;

import java.text.DateFormat;
import java.util.Date;

import static com.banixc.fsc.model.base.Function.formatToData;

/**
 * Created by Lenovo on 2016/8/16.
 */
public class Mark extends Result{


    /**
     * mark : 99
     * student_name : 悟能
     * course_name : 语文
     * exam_name : 2016夏季第一次月考
     * time : 2016-08-08
     */

    private String mark;
    private String student_name;
    private String course_name;
    private String exam_name;
    private String time;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    public String getTime() {
        return time;
    }



}
