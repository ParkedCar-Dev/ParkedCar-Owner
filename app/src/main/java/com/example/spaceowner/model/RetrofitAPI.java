package com.example.spaceowner.model;

import com.example.spaceowner.model.data.LoginRequest;
import com.example.spaceowner.model.data.LoginResponse;
import com.example.spaceowner.model.data.SignupRequest;
import com.example.spaceowner.model.data.SignupResponse;
import com.example.spaceowner.model.repositories.SignupRepository;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {
    @POST("/auth/login/")
    Call<LoginResponse> login(@Body LoginRequest loginReq);

    @POST("/register/")
    Call<SignupResponse> signup(@Body SignupRequest signupReq);
}
