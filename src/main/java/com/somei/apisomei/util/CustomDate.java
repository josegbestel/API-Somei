package com.somei.apisomei.util;

import java.io.Serializable;
import java.time.LocalDate;

public class CustomDate implements Serializable {

    private int day;
    private int mounth;
    private int year;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMounth() {
        return mounth;
    }

    public void setMounth(int mounth) {
        this.mounth = mounth;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public LocalDate toLocalDate(){
        return LocalDate.of(this.year, this.mounth, this.day);
    }

    static public CustomDate byLocalDate(LocalDate date){
        CustomDate dateCustom = new CustomDate();
        dateCustom.setMounth(date.getMonthValue());
        dateCustom.setYear(date.getYear());
        dateCustom.setDay(date.getDayOfMonth());

        return dateCustom;
    }
}
