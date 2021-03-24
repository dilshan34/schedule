package com.example.shedule.Models;

public class getsetDatePicker {

    String id,fromtime,totime;

    public getsetDatePicker(String id, String fromtime, String totime) {
        this.id = id;
        this.fromtime = fromtime;
        this.totime = totime;

    }

    public String getId() {
        return id;
    }

    public String getFromtime() {
        return fromtime;
    }

    public String getTotime() {
        return totime;
    }
}
