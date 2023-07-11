package com.example.cmd.request;

public class ChangeMyInfoRequest {

    private String name;
    private Long classIdNumber;
    private Long birth;
    private String majorField;
    private String clubName;

    public ChangeMyInfoRequest(String name, Long classIdNumber, Long birth, String majorField, String clubName){
        this.name = name;
        this.classIdNumber = classIdNumber;
        this.birth = birth;
        this.majorField = majorField;
        this.clubName = clubName;
    }
}