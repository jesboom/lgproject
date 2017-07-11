package org.androidtown.schedule;

/**
 * Created by ohji1 on 2017-07-08.
 */

public class Schedule
{
    int year;
    int mounth;
    int day;
    String body;

    public Schedule(){};

    public Schedule(String body, int year, int mounth, int day) {
        this.year = year;
        this.mounth = mounth;
        this.day = day;
        this.body = body;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
