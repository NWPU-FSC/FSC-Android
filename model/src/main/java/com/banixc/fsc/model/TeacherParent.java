package com.banixc.fsc.model;

/**
 * Created by Lenovo on 2016/8/22
 */
public class TeacherParent extends Result{


    /**
     * student_id : 82
     * parent_id : 1
     * status : 2
     * relation_id : 1
     * relation_type_name : 父亲
     * relation_status_name : 未审核
     * leader : 1
     * student_name : 李四
     * parent_name : 闫正阳
     * parent_username : banixc
     */

    private int student_id;
    private int parent_id;
    private int status;
    private String relation_type_name;
    private String relation_status_name;
    private String student_name;
    private String parent_name;
    private String parent_username;

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRelation_type_name() {
        return relation_type_name;
    }

    public void setRelation_type_name(String relation_type_name) {
        this.relation_type_name = relation_type_name;
    }

    public String getRelation_status_name() {
        return relation_status_name;
    }

    public void setRelation_status_name(String relation_status_name) {
        this.relation_status_name = relation_status_name;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public String getParent_username() {
        return parent_username;
    }

    public void setParent_username(String parent_username) {
        this.parent_username = parent_username;
    }
}
