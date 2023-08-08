package com.example.cmd.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScheduleItemResponse {
    @SerializedName("ITRT_CNTNT")
    @Expose
    private String itrtCntnt;

    @SerializedName("PERIO")
    @Expose
    private String perio;

    public String getItrtCntnt() {
        return itrtCntnt;
    }

    public String getPerio() {
        return perio;
    }

    @Override
    public String toString() {
        return "시간표: " + itrtCntnt;
    }
}
