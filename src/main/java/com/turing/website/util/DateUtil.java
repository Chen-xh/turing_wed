package com.turing.website.util;

import com.turing.website.enums.CustomizeErrorCode;
import com.turing.website.enums.MyCustomizeErrorCode;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Jack
 * @date 2019-07-28-14:27
 */
public class DateUtil {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static volatile DateUtil instance;

    private DateUtil(){}

    public static synchronized DateUtil getInstance(){
        if(instance == null){
            synchronized (DateUtil.class){
                instance = new DateUtil();
            }
        }
        return instance;
    }

    public static Date getCurrentDate(){

        LocalDateTime now = LocalDateTime.now();
        String currentTime = DATE_FORMATTER.format(now);
        DateFormat dateFormat = DateFormat.getDateInstance();
        try {
            Date date = dateFormat.parse(currentTime);
            return date;
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public static Date getCurrentDateTime(){

        LocalDateTime now = LocalDateTime.now();
        String currentTime = DATE_TIME_FORMATTER.format(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(currentTime);
            return date;
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public static String getCornByTime(Date times){
        String formatTimeStr = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("ss mm HH dd MM ?");

            if (times != null) {
                formatTimeStr = sdf.format(times);
            }
        } catch (Exception e) {
            throw new RuntimeException(String.valueOf(MyCustomizeErrorCode.Date_FORMAT_REEOR));
        }
        return formatTimeStr;
    }


}
