package com.backend.EEA.utils;

import java.util.Calendar;
import java.util.Date;

public final class DateUtils {
    private DateUtils(){}
    public static Date getNextDayDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    public static Date getPreviousDayDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1); //minus number would decrement the days
        return cal.getTime();
    }

    public static Date getLastYearDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, -1); //minus number would decrement the year
        return cal.getTime();
    }

}
