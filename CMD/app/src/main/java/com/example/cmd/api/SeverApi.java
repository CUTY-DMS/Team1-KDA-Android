package com.example.cmd.api;


import com.example.cmd.request.ChangeMyInfoRequest;
import com.example.cmd.request.ChangePasswordRequest;
import com.example.cmd.request.LoginRequest;
import com.example.cmd.request.SignupRequest;
import com.example.cmd.response.AllNoticeResponse;
import com.example.cmd.response.LoginResponse;
import com.example.cmd.response.MypageResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SeverApi {

    @POST("/user/signup")  //회원 가입
    Call<Void> signup (
            @Body SignupRequest signupRequest
    );

    @POST("/user/login")   //로그인
    Call<LoginResponse> login (
            @Body LoginRequest loginRequest
    );

    @GET("/user/myPage")   //마이페이지
    Call<MypageResponse> myPage (
            @Header("Authorization") String accessToken
    );

    @PATCH("/user/motifyUserInfo")  //내 정보 변경
    Call<Void> changeInfo (
            @Header("Authorization") String accessToken,
            @Body ChangeMyInfoRequest changeMyInfoRequest
    );

    @PATCH("/admin/password/change")  //비밀번호 변경
    Call<Void> changePassword (
            @Header("Authorization") String accessToken,
            @Body ChangePasswordRequest changePasswordRequest
    );


    @GET("/user/allNoti")  //전체 공지 리스트 확인
    Call<List<AllNoticeResponse>> allNotice (
            @Header("Authorization") String Token
    );

}
