package com.example.spaceowner.model.data.booking;

import com.google.gson.annotations.SerializedName;

public class BookingDetailsRequest {
    @SerializedName("booking_id")
    int bookingId;

    @SerializedName("rating")
    double rating;

    public BookingDetailsRequest(int bookingId) {
        this.bookingId = bookingId;
    }

    public BookingDetailsRequest(int bookingId, double rating) {
        this.bookingId = bookingId;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "BookingDetailsRequest{" +
                "bookingId=" + bookingId +
                ", rating=" + rating +
                '}';
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
}
