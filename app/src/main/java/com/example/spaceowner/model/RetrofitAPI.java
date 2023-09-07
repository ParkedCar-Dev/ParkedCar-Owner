package com.example.spaceowner.model;

import com.example.spaceowner.model.data.BookingRequest;
import com.example.spaceowner.model.data.GenericResponse;
import com.example.spaceowner.model.data.SpaceBookings;
import com.example.spaceowner.model.data.auth.LoginRequest;
import com.example.spaceowner.model.data.auth.LoginResponse;
import com.example.spaceowner.model.data.auth.SignupRequest;
import com.example.spaceowner.model.data.auth.SignupResponse;
import com.example.spaceowner.model.data.Space;
import com.example.spaceowner.model.data.SpaceListResponse;
import com.example.spaceowner.model.data.SpaceStatusUpdateRequest;
import com.example.spaceowner.model.data.requests.SpaceRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @POST("/space/add/")
    Call<GenericResponse> addNewSpace(@Body Space space);

    @POST("/space/changeSpaceStatus")
    Call<GenericResponse> updateStatus(@Body SpaceStatusUpdateRequest request);

    @POST("/space/update")
    Call<GenericResponse> updateSpace(@Body Space space);

    @POST("/booking/space_bookings")
    Call<SpaceBookings> getSpaceBookings(@Body BookingRequest request);
}
