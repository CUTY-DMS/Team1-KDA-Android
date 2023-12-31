package com.example.cmd.api;


import com.example.cmd.request.ChangeMyInfoRequest;
import com.example.cmd.request.ChangePasswordRequest;
import com.example.cmd.request.LoginRequest;
import com.example.cmd.request.SignupRequest;
import com.example.cmd.response.AllNoticeResponse;
import com.example.cmd.response.CalendarResponse;
import com.example.cmd.response.GradeClassResponse;
import com.example.cmd.response.LoginResponse;
import com.example.cmd.response.MypageResponse;
import com.example.cmd.response.NoticeCheckResponse;
import com.example.cmd.response.ReissueResponse;
import com.example.cmd.response.ScheduleResponse;
import com.example.cmd.response.WeClassResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SeverApi {

    @POST("/user/signup")//회원 가입
    Call<Void> signup(
            @Body SignupRequest signupRequest
    );

    @POST("/user/login")//로그인
    Call<LoginResponse> login(
            @Body LoginRequest loginRequest
    );

    @GET("/user/myPage")//마이페이지
    Call<MypageResponse> myPage(
            @Header("Authorization") String accessToken
    );

    @PATCH("/user/infoChange")//내 정보 변경
    Call<Void> changeInfo(
            @Header("Authorization") String accessToken,
            @Body ChangeMyInfoRequest changeMyInfoRequest
    );

    @PATCH("/user/password/change")//비밀번호 변경
    Call<Void> changePassword(
            @Header("Authorization") String accessToken,
            @Body ChangePasswordRequest changePasswordRequest
    );

    @GET("/user/allNoti")//전체 공지 리스트 확인
    Call<List<AllNoticeResponse>> allNotice(
            @Header("Authorization") String accessToken
    );

    @GET("/user/classNoti")//우리 반 공지 리스트 확인
    Call<List<WeClassResponse>> weClass(
            @Header("Authorization") String accessToken
    );

    @GET("/noti/check/{notiId}")//공지 상세 보기
    Call<NoticeCheckResponse> check(
            @Header("Authorization") String accessToken,
            @Path("notiId") long id
    );

    @GET("hisTimetable?ATPT_OFCDC_SC_CODE=G10&SD_SCHUL_CODE=7430310&Type=json")//시간표
    Call<ScheduleResponse> scheduleList(
            @Query("GRADE") String grade,
            @Query("CLASS_NM") String classNm,
            @Query("ALL_TI_YMD") String date,
            @Query("KEY") String key
    );

    @GET("/user/schedule/{year}/{month}")//달력 일정 보기
    Call<List<CalendarResponse>> calendar(
            @Header("Authorization") String accessToken,
            @Path("year") int year,
            @Path("month") int mont
    );

    @GET("/user/gradeClass")//학년 반 보기
    Call<GradeClassResponse> grade(
            @Header("Authorization") String accessToken
    );

    @POST("/reissue")//토큰 재발급
    Call<ReissueResponse> reissue(
            @Header("AUTHORIZATION_HEADER") String refreshToken
    );

}
