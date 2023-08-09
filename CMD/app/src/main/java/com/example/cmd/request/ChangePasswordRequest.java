package com.example.cmd.request;

public class ChangePasswordRequest {

    private String oldPassword; //예전 비밀번호
    private String newPassword; //비밀번호 입력
    private String reNewPassword; //비밀번호 확인

    public ChangePasswordRequest(String oldPassword, String newPassword, String reNewPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.reNewPassword = reNewPassword;
    }
}
