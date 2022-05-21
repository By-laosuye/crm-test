package com.bjpowernode.crm.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 对Date类型专门处理的类
 */
public class DateUtils {

    /**
     * 对指定date对象进行格式化："yyyy-MM-dd HH:mm:ss"
     * @param date
     * @return
     */
    public static String formateDateTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        return dateStr;
    }

    public static String formateDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateStr = sdf.format(date);
        return dateStr;
    }

    public static String formateTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        String dateStr = sdf.format(date);
        return dateStr;
    }
}
