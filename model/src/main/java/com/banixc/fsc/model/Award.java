package com.banixc.fsc.model;

import java.util.Date;

import static com.banixc.fsc.model.base.Function.formatToData;

/**
 * Created by Lenovo on 2016/8/16.
 */
public class Award extends Result {


    /**
     * id : 9
     * name : 全国物理竞赛一等奖
     * description :
     * date : 2016-08-14 15:29:15
     * remark : null
     * award_type : 1
     * type_name : 国家奖励
     */

    private String id;
    private String name;
    private String description;
    private Date date;
    private Object remark;
    private String award_type;
    private String type_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return formatToData(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public String getAward_type() {
        return award_type;
    }

    public void setAward_type(String award_type) {
        this.award_type = award_type;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
}
