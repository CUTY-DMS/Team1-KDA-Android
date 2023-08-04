package com.example.cmd.response;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScheduleHisTimetable {

    @SerializedName("row")
    @Expose
    private List<ScheduleItemResponse> scheduleItems = null;

    public List<ScheduleItemResponse> getScheduleItems() {
        return scheduleItems;
    }

    @Override
    public String toString() {
        return "scheduleItems: " + scheduleItems;
    }
}
