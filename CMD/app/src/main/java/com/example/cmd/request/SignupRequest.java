package com.example.cmd.request;

public class SignupRequest {
    private String name;
    private String email;
    private String password;
    private String classId;
    private String birth;
    private String majorField;
    private String clubName;

    public SignupRequest(String name, String email, String password, String classId, String birth, String majorField, String clubName) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.classId = classId;
        this.birth = birth;
        this.majorField = majorField;
        this.clubName = clubName;
    }
}
