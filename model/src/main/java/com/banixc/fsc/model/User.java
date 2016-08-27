package com.banixc.fsc.model;

/**
 * Created by Lenovo on 2016/8/16.
 */
public class User extends Result {


    private int id;
    private String name;


    private int ischoise = 0;

    // 登录Account的属性
    private String token;

    // 老师的属性

    private int course_id;
    private String course_name;
    /**
     * user detail
     * username : zhangsan
     * gender : F
     * email : 2789698789@qq.com
     * phone : 46798609
     * status : 1
     * access : 300
     */

    private String username;
    private String gender;
    private String email;
    private String phone;
    private int status;
    private int access;

    // 家长的属性

    //


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public int getIschoise() {
        return ischoise;
    }

    public void setIschoise(int ischoise) {
        this.ischoise = ischoise;
    }

}
