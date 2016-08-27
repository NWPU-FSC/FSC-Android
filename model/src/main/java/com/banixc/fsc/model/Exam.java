package com.banixc.fsc.model;

import java.io.Serializable;
import java.util.Date;

import static com.banixc.fsc.model.base.Function.formatToData;

/**
 * Created by Lenovo on 2016/8/20.
 */
public class Exam extends Result {


    private String exam_name;
    private int exam_id;
    private String time;

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
