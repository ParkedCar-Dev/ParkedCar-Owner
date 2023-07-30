package com.example.spaceowner.model;

import com.example.spaceowner.model.data.LoginRequest;
import com.example.spaceowner.model.data.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {
    @POST("/auth/login/")
    Call<LoginResponse> login(@Body LoginRequest loginReq);
}
