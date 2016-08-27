package com.banixc.fsc.model.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Function {

    private static SimpleDateFormat dataTimeFormat;
    private static SimpleDateFormat dataFormat;
    private static SimpleDateFormat TimeFormat;



    private static SimpleDateFormat getDataTimeFormat(){
        if(dataTimeFormat==null){
            dataTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        }
        return dataTimeFormat;
    }

    private static SimpleDateFormat getDataFormat(){
        if(dataFormat==null){
            dataFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        }
        return dataFormat;
    }

    private static SimpleDateFormat getTimeFormat(){
        if(TimeFormat==null){
            TimeFormat = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
        }
        return TimeFormat;
    }

    public static String formatToDataTime(Date date){
        return getDataTimeFormat().format(date);
    }

    public static String formatToData(Date date){
        return getDataFormat().format(date);
    }
    public static String formatToTime(Date date){
        return getTimeFormat().format(date);
    }

}
