package com.example.cmd.response;


import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScheduleResponse {

//    @SerializedName("GRADE")
//    @Expose
//    private String grade;
//    @SerializedName("CLASS_NM")
//    @Expose
//    private String classNm;
//    @SerializedName("PERIO")
//    @Expose
//    private String perio;
//    @SerializedName("ITRT_CNTNT")
//    @Expose
//    private String itrtCntnt;

    //---------------------------
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

    //    public String getGrade() {
//        return grade;
//    }
//
//    public void setGrade(String grade) {
//        this.grade = grade;
//    }
//
//    public String getClassNm() {
//        return classNm;
//    }
//
//    public void setClassNm(String classNm) {
//        this.classNm = classNm;
//    }
//
//    public String getPerio() {
//        return perio;
//    }
//
//    public void setPerio(String perio) {
//        this.perio = perio;
//    }
//
//    public String getItrtCntnt() {
//        return itrtCntnt;
//    }
//
//    public void setItrtCntnt(String itrtCntnt) {
//        this.itrtCntnt = itrtCntnt;
//    }
//
//    @Override
//    public String toString() {
//        return "ScheduleResponse{" +
//                "grade='" + grade + '\'' +
//                ", classNm='" + classNm + '\'' +
//                ", perio='" + perio + '\'' +
//                ", itrtCntnt=" + itrtCntnt +
//                '}';
//
//
//    }
}
