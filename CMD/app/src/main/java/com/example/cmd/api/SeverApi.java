package com.example.cmd.api;

import retrofit2.Call;
import retrofit2.http.POST;

public interface SeverApi {

    @POST("/user/signup")
    Call<Void> signup (

    )
}
