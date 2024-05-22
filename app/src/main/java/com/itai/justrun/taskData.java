package com.itai.justrun;

public class taskData{
    String name;
    String date;
    String duration;

    String startTime;

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }
    public String getStartTime() {return startTime;}


    public String getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }


    // No-argument constructor
    public taskData() {
    }
    taskData(String name, String date, String duration, String startTime)
    {
        this.name= name;
        this.date= date;
        this.duration= duration;
        this.startTime= startTime;
    }
}
