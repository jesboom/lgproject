package org.androidtown.schedule;

/**
 * Created by Ei Seok on 2017-07-14.
 */

public class VoteData {
    String shedule_title;
    String shedule_body;
    int year;
    int mounth;
    int day;

    public VoteData(){}
    public VoteData(String shedule_title, String shedule_body, int day, int mounth, int year)
    {
        this.shedule_title = shedule_title;
        this.shedule_body = shedule_body;
        this.day = day;
        this.mounth = mounth;
        this.year = year;
    }
    public String getshedule_title() {
        return shedule_title;
    }
    public void setshedule_title(String shedule_title) {
        this.shedule_title = shedule_title;
    }

    public String getshedule_body() {
        return shedule_body;
    }

    public void setshedule_body(String shedule_body) {
        this.shedule_body = shedule_body;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMounth() {
        return mounth;
    }

    public void setMounth(int mounth) {
        this.mounth = mounth;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }


}
