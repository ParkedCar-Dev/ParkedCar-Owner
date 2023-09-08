package com.example.spaceowner.model.data.booking;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class BookingDetailsResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("booking")
    private Booking booking;
    public BookingDetailsResponse(String status, String message, Booking booking) {
        this.status = status;
        this.message = message;
        this.booking = booking;
    }

    @NonNull
    @Override
    public String toString() {
        return "BookingResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", booking=" + booking +
                '}';
    }

    public Booking getBooking(){
        return booking;
    }
}
