package com.example.bookshoppingapp.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /**
     * 获取当前日期时间
     * @return 当前日期时间
     * @throws ParseException
     */
    public static String getDate() {
        SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date;
        date = df.format(new Date());
        return date;
    }

    public static Date getDateByStr(String str) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月份是MM
        Date date = simpleDateFormat.parse(str);

        return date;
    }

}
