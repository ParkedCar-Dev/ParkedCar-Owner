package com.example.spaceowner.model;

import com.example.spaceowner.model.data.GenericResponse;
import com.example.spaceowner.model.data.LoginRequest;
import com.example.spaceowner.model.data.LoginResponse;
import com.example.spaceowner.model.data.SignupRequest;
import com.example.spaceowner.model.data.SignupResponse;
import com.example.spaceowner.model.data.Space;
import com.example.spaceowner.model.data.SpaceListResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitAPI {
    @POST("/auth/login/")
    Call<LoginResponse> login(@Body LoginRequest loginReq);

    @POST("/register/")
    Call<SignupResponse> signup(@Body SignupRequest signupReq);

    @GET("/space/getMySpaces/")
    Call<SpaceListResponse> getSpaces();

    @GET("/space/getActiveSpaces/")
    Call<SpaceListResponse> getActiveSpaces();

    @GET("/space/getDisabledSpaces/")
    Call<SpaceListResponse> getDisabledSpaces();

    @GET("/space/getRequestedSpaces/")
    Call<SpaceListResponse> getRequestedSpaces();


    @POST("/space/add2/")
    Call<Space> addNewSpaceOld(@Body Space space);

    @POST("/space/add/")
    Call<GenericResponse> addNewSpace(@Body Space space);
}
