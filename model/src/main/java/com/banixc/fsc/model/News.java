package com.banixc.fsc.model;

import java.io.Serializable;
import java.util.Date;

import static com.banixc.fsc.model.base.Function.formatToData;

public class News extends Result implements Serializable{


    /**
     * id : 7
     * title : 测试
     * type : 1
     * content : 正玩呢
     * sender : 1
     * is_stick : 0
     * time : 2016-08-09 22:57:14
     * edit_time : 0000-00-00 00:00:00
     * type_name : 新闻
     * sender_name : 闫正阳
     */

    private int id;
    private String title;
    private int type;
    private String content;
    private int sender;
    private int is_stick;
    private Date time;
    private Date edit_time;
    private String type_name;
    private String sender_name;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public int getSender() {
        return sender;
    }

    public boolean is_stick() {
        return is_stick == 1;
    }

    public String getTime() {
        return formatToData(time);
    }

    public Date getEdit_time() {
        return edit_time;
    }

    public String getType_name() {
        return type_name;
    }

    public String getSender_name() {
        return sender_name;
    }
}
