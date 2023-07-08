package com.example.cmd.api;

import com.example.cmd.request.LoginRequest;
import com.example.cmd.request.SignupRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SeverApi {

    @POST("/user/signup")
    Call<Void> signup (
            @Body SignupRequest signupRequest
            );

    @POST("/user/login")
    Call<Void> login (
            @Body LoginRequest loginRequest
    );
}
