package com.ewallet.springbootewallet.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static String getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss a");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }
}
