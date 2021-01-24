package com.yuong.demo;

public class DateTimeModel {
    private int year;
    private int month;
    private int day;
    private String date;

    public DateTimeModel(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.date = year + "-" + (month > 9 ? ("" + month) : ("0" + month)) + "-" + (day > 9 ? ("" + day) : ("0" + day));
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return date;
    }
}

