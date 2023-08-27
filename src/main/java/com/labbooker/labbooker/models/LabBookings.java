package com.labbooker.labbooker.models;

import java.sql.Time;
import java.sql.Date;

public class LabBookings {

    private int id;
    private String labName;
    private Date date;
    private Time startTime;
    private String className;
    private Time endTime;

    public LabBookings( int id,String labName, Date date, Time startTime, String className, Time endTime) {
        this.id = id;
        this.labName = labName;
        this.date = date;
        this.startTime = startTime;
        this.className = className;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
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

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}

