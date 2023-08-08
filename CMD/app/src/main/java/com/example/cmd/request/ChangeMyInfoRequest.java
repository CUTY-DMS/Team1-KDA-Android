package com.example.cmd.request;

public class ChangeMyInfoRequest {

    private String name;
    private Long classId;
    private Long birth;
    private String majorField;
    private String clubName;

    public ChangeMyInfoRequest(String name, Long classId, Long birth, String majorField, String clubName) {
        this.name = name;
        this.classId = classId;
        this.birth = birth;
        this.majorField = majorField;
        this.clubName = clubName;
    }
}
