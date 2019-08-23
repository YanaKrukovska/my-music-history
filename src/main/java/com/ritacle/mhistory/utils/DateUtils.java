package com.ritacle.mhistory.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {


    private DateUtils() {
    }


    public static Date createDate(int year, int month, int day, int hour, int min) {
        TimeZone.setDefault(TimeZone.getTimeZone("CET"));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day, hour, min, 0);
        return calendar.getTime();
    }

    public static Date createDate(int year, int month, int day) {
        TimeZone.setDefault(TimeZone.getTimeZone("CET"));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day, 0, 0, 0);
        return calendar.getTime();
    }

    public static Timestamp atStartOfDate(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Timestamp.valueOf(localDate.atStartOfDay());

    }

    public static Timestamp atEndOfDate(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1);
        return Timestamp.valueOf(localDate.atStartOfDay().minusSeconds(1));

    }


    public static Timestamp atStartOfNextDate(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1);
        return Timestamp.valueOf(localDate.atStartOfDay());

    }

    public static Timestamp createTimestamp(int year, int month, int day, int hour, int min) {
        LocalDateTime localDate = LocalDateTime.of(year, month, day, hour, min, 0, 0);
        return Timestamp.valueOf(localDate);

    }

    public static int getYear(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getYear();
    }

    public static int getMonth(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getMonthValue();
    }

    public static int getDay(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getDayOfMonth();
    }
}
