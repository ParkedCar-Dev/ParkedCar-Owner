package com.example.spaceowner.model.data;

import com.google.gson.annotations.SerializedName;

public class BookingDetailsRequest {
    @SerializedName("booking_id")
    int bookingId;

    public BookingDetailsRequest(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
}
