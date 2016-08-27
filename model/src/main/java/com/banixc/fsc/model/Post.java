package com.banixc.fsc.model;

import java.util.Date;

import static com.banixc.fsc.model.base.Function.formatToDataTime;

/**
 * Created by Banixc on 2016/8/23.
 */

public class Post {

    private String content;
    private int sender;
    private Date time;
    private String sender_name;

    public String getContent() {
        return content;
    }

    public int getSender() {
        return sender;
    }

    public String getTime() {
        return formatToDataTime(time);
    }

    public String getSender_name() {
        return sender_name;
    }

    public String getName_time()
    {
        return getSender_name()+"@"+getTime();
    }
}
