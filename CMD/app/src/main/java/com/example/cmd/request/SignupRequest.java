package com.example.cmd.request;

import android.util.Log;

public class SignupRequest {
    private String username;
    private String email;
    private String password;

    public SignupRequest(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return  username;
    }

    public String getEmail() {
        return  email;
    }

    public String getPassword() {
        return password;
    }
}
