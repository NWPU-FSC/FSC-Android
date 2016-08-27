package com.banixc.fsc.model;

/**
 * Created by Lenovo on 2016/8/16.
 */
public class ParentStudent {

    /**
     * parent_id : 11
     * student_id : 83
     * relation_id : 1
     * status : 1
     * parent_name : 李刚
     * student_name : 王二
     * relation_name : 父亲
     */

    private int parent_id;
    private int student_id;
    private int relation_id;
    private String status;
    private String parent_name;
    private String student_name;
    private String relation_name;
    private boolean is_current_student;

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(int relation_id) {
        this.relation_id = relation_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getRelation_name() {
        return relation_name;
    }

    public void setRelation_name(String relation_name) {
        this.relation_name = relation_name;
    }

    public boolean is_current_student() {
        return is_current_student;
    }
}
