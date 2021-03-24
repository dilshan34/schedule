package com.example.shedule.Models;

public class getsetShedule {

    String id,fromTime,toTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public getsetShedule(String id, String fromTime, String toTime) {
        this.id = id;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }
}

