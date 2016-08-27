package com.banixc.fsc.model;

import com.banixc.fsc.model.Receiver;

import static com.banixc.fsc.model.base.Function.formatToData;
import static com.banixc.fsc.model.base.Function.formatToDataTime;

import java.util.Date;
import java.util.List;

/**
 * Created by Lenovo on 2016/8/17.
 */
public class Message extends Result{


    /**
     * id : 4
     * content : 测试一下
     * sender : 1
     * time : 2016-08-21 21:28:35
     * title : 策划书
     * receiver : [{"id":"85","name":"刁进平"}]
     * sender_name : 闫正阳
     */

    private int id;
    private String content;
    private int sender;
    private Date time;
    private String title;
    private String sender_name;
    /**
     * id : 85
     * name : 刁进平
     */

//    private List<Receiver> receiver;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return String.valueOf(sender);
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public String getTime() {
        return formatToDataTime(time);
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

//    public List<Receiver> getReceiver() {
//        return receiver;
//    }
//
//    public void setReceiver(List<Receiver> receiver) {
//        this.receiver = receiver;
//    }

    public String getSend_time(){
        return getSender_name()+"@"+getTime();
    }

}
