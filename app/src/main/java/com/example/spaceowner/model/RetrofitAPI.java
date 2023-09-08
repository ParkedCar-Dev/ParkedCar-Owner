package com.example.spaceowner.model;

import com.example.spaceowner.model.data.booking.BookingDetailsRequest;
import com.example.spaceowner.model.data.booking.BookingListRequest;
import com.example.spaceowner.model.data.booking.BookingDetailsResponse;
import com.example.spaceowner.model.data.GenericResponse;
import com.example.spaceowner.model.data.booking.BookingListResponse;
import com.example.spaceowner.model.data.auth.LoginRequest;
import com.example.spaceowner.model.data.auth.LoginResponse;
import com.example.spaceowner.model.data.auth.SignupRequest;
import com.example.spaceowner.model.data.auth.SignupResponse;
import com.example.spaceowner.model.data.Space;
import com.example.spaceowner.model.data.SpaceListResponse;
import com.example.spaceowner.model.data.SpaceStatusUpdateRequest;

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

    @POST("/space/add/")
    Call<GenericResponse> addNewSpace(@Body Space space);

    @POST("/space/changeSpaceStatus")
    Call<GenericResponse> updateStatus(@Body SpaceStatusUpdateRequest request);

    @POST("/space/update")
    Call<GenericResponse> updateSpace(@Body Space space);

    @POST("/booking/space_bookings")
    Call<BookingListResponse> getSpaceBookings(@Body BookingListRequest request);

    @POST("/booking/details")
    Call<BookingDetailsResponse> getBookingDetails(@Body BookingDetailsRequest request);
}
