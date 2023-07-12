package com.example.cmd.request;

public class ChangeMyInfoRequest {

    private String name;
    private String classIdNumber;
    private String birth;
    private String majorField;
    private String clubName;

    public ChangeMyInfoRequest(String name, String classIdNumber, String birth, String majorField, String clubName){
        this.name = name;
        this.classIdNumber = classIdNumber;
        this.birth = birth;
        this.majorField = majorField;
        this.clubName = clubName;
    }
}
