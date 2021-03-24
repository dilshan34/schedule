package com.example.shedule.Models;

import com.google.gson.annotations.SerializedName;

public class getsetChart {

    @SerializedName("id")
    int taskId;

    @SerializedName("task")
    String task;

    public int getTaskId() {
        return taskId;
    }

    public String getTask() {
        return task;
    }
}
