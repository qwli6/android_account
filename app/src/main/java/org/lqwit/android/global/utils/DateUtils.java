package org.lqwit.android.global.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * 将日期类型转换成对应的模式字符串
     * @param sourceDate  原日期
     * @param pattern 转换模式
     * @return
     * @throws Exception
     */
    public static String format(Date sourceDate, String pattern) throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(sourceDate);
    }


    public static String format(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String formatNoYear(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        return format;
    }

    public static String format2(String format) {
        String time = format.split("-")[1] + "月" + format.split("-")[2] + "日";
        return time;
    }
}
