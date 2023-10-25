package com.okay.appplatform.tools;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

public class GetTime {

    private static final Logger logger = LoggerFactory.getLogger(GetTime.class);


    public static String getTime() {

        String time = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());

        return time;
    }

    public static String getTime(Long addTime) {

        String time = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(System.currentTimeMillis() + addTime);

        return time;
    }

    public static String getStartTime(Long startTime) {

        String time = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime);
        return time;
    }


    public static String elapsedTime(Long elspsedTime) {

        String time = new java.text.SimpleDateFormat("mm:ss").format(elspsedTime);
        int minute = Integer.parseInt(time.split(":")[0]);
        int second = Integer.parseInt(time.split(":")[1]);
        if (minute > 0) {
            time = minute + "分" + second + "秒";
        } else {
            time = second + "秒";
        }

        return time;
    }
    /**
     * @return 获取年月日
     */
    public static String getYmd() {

        String time = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date());

        return time;
    }


    /**
     * @return 获取年月日
     */
    public static Date getYmdForDate() {

        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    public static String getTimeFormat() {

        String time = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        return time;
    }
}
