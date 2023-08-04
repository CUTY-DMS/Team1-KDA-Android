package com.example.cmd.response;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScheduleItemResponse {
    @SerializedName("ITRT_CNTNT")
    @Expose
    private String itrtCntnt;

    public String getItrtCntnt() {
        return itrtCntnt;
    }


    @Override
    public String toString() {
        return "시간표: " + itrtCntnt;
    }
}
