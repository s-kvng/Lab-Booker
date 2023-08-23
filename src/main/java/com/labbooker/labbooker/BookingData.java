package com.labbooker.labbooker;

import java.sql.Date;

public class BookingData {

    private String lab;

    private Date data;

    private String startTime;

    private String endTime;

    private String className;

    public BookingData(String lab, Date data, String startTime, String endTime, String className) {
        this.lab = lab;
        this.data = data;
        this.startTime = startTime;
        this.endTime = endTime;
        this.className = className;
    }


    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
