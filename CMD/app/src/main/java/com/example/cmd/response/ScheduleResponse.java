package com.example.cmd.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScheduleResponse {

    @SerializedName("hisTimetable")
    @Expose
    private List<ScheduleHisTimetable> hisTimetable;

    public List<ScheduleHisTimetable> getHisTimetable() {
        return hisTimetable;
    }

    @Override
    public String toString() {
        return "scheduleItems: " + hisTimetable;
    }
}
