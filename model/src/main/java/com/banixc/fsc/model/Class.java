package com.banixc.fsc.model;

/**
 * Created by Lenovo on 2016/8/18.
 */
public class Class {


    /**
     * id : 1
     * name : 三年级一班
     * data : 2013-07-09
     * leader : 1
     * leader_name : 张三
     */

    private int id;
    private String name;
    private String data;
    private int leader;
    private String leader_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getLeader() {
        return leader;
    }

    public void setLeader(int leader) {
        this.leader = leader;
    }

    public String getLeader_name() {
        return leader_name;
    }

    public void setLeader_name(String leader_name) {
        this.leader_name = leader_name;
    }
}
