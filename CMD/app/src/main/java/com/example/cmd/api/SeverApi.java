package com.example.cmd.api;


import com.example.cmd.request.ChangeMyInfoRequest;
import com.example.cmd.request.LoginRequest;
import com.example.cmd.request.SignupRequest;
import com.example.cmd.response.MypageResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SeverApi {

    @POST("/user/signup")
    Call<Void> signup (
            @Body SignupRequest signupRequest
    );

    @POST("/user/login")
    Call<Void> login (
            @Body LoginRequest loginRequest
    );

    @GET("/user/myPage")
    Call<MypageResponse> myPage (

    );

    @PATCH("/user/motifyUserInfo")
    Call<Void> changeInfo (
            @Header("Authorization") String accessToken,
            @Body ChangeMyInfoRequest changeMyInfoRequest
    );
}
