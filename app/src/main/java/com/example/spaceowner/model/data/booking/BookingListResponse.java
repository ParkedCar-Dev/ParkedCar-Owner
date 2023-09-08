package com.example.spaceowner.model.data.booking;


//{
//        "status":"success",
//        "message":"get bookings successful",
//        "bookings":[]
//        }

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kotlin.collections.ArrayDeque;

public class BookingListResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("bookings")
    private List<Booking> bookings;

    public BookingListResponse(String status, String message, List<Booking> bookings) {
        this.status = status;
        this.message = message;
        this.bookings = bookings;
    }

    public BookingListResponse() {
        this.status = "success";
        this.message = "get bookings successful";
        this.bookings = new ArrayDeque<>();
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    @Override
    public String toString() {
        return "SpaceBookings{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", bookings=" + Arrays.toString(bookings.toArray()) +
                '}';
    }
}
