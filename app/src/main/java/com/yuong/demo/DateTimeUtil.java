package com.yuong.demo;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class DateTimeUtil {
    public static final String DATE = "yyyy-MM-dd";

    /**
     * 获取年月日
     *
     * @param date
     * @return
     */
    public static int[] getDateTime(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new int[]{year, month, day};
    }

    /**
     * 基于日期获取某个时期
     *
     * @param date   基准日期
     * @param offset 偏移量
     * @return
     */
    public static Date calculateDate(Date date, int offset) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(GregorianCalendar.DATE, offset);
        return gc.getTime();
    }

    private static DateTimeModel getDateTimeModel(Date date) {
        int[] dateArray = getDateTime(date);
        return new DateTimeModel(dateArray[0], dateArray[1], dateArray[2]);
    }

    /**
     * 获取日期
     *
     * @param dateStr
     */
    public static Date getDate(String dateStr) {
        if (TextUtils.isEmpty(dateStr)) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(DATE, Locale.getDefault());
        Date date = null;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取当前的日期
     *
     * @return
     */
    public static String getCurrentDate() {
        SimpleDateFormat df = new SimpleDateFormat(DATE, Locale.getDefault());
        return df.format(new Date());
    }

    public static List<DateTimeModel> getDateTimeModels(String dateStr) {
        Date date = getDate(dateStr);
        if (date == null) return null;

        List<DateTimeModel> dateTimeModels = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            dateTimeModels.add(getDateTimeModel(calculateDate(date, i - 30)));
        }

        dateTimeModels.add(getDateTimeModel(date));

        for (int i = 1; i <= 30; i++) {
            dateTimeModels.add(getDateTimeModel(calculateDate(date, i)));
        }
        return dateTimeModels;
    }
}
