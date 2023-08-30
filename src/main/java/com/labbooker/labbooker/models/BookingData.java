package com.labbooker.labbooker.models;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class BookingData {

    private String lab;

    private Date date;

    private Time startTime;

    private Time endTime;

    private String className;

    public BookingData(String lab, LocalDate date, String startTime, String endTime, String className) {
        this.lab = lab;
        this.date = Date.valueOf(date);
        this.startTime = Time.valueOf(startTime);
        this.endTime = Time.valueOf(endTime);
        this.className = className;
    }


    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = Time.valueOf(startTime);
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = Time.valueOf(endTime);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
