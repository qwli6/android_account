package org.lqwit.android.global.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {


    public static String format(Date date){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(date);
    }

    public static String formatNoYear(Date date){
        return new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(date);
    }

    public static String formatNoDay(Date date){
        return new SimpleDateFormat("yyyy-MM", Locale.CHINA).format(date);
    }

    public static String format2(String format) {
        String time = format.split("-")[1] + "月" + format.split("-")[2] + "日";
        return time;
    }
}
