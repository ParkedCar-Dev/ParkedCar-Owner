package com.example.spaceowner.model.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.spaceowner.model.RetrofitAPI;
import com.example.spaceowner.model.RetrofitClient;
import com.example.spaceowner.model.data.Booking;
import com.example.spaceowner.model.data.BookingDetailsRequest;
import com.example.spaceowner.model.data.BookingResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingRepository {
    private static BookingRepository bookingRepository;
    private BookingRepository(){}
    public static BookingRepository getInstance(){
        if(bookingRepository == null) bookingRepository = new BookingRepository();
        return bookingRepository;
    }

    public void getBookingDetails(int bookingId, MutableLiveData<Booking> currentBooking) {
        Call<BookingResponse> call = RetrofitClient.getInstance().create(RetrofitAPI.class).getBookingDetails(new BookingDetailsRequest(bookingId));
        call.enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                Log.d("BookingRepositoryBookingDetails", "onResponse: " + response.body());
                if(response.isSuccessful()){
                    currentBooking.setValue(response.body().getBooking());
                }
            }

            @Override
            public void onFailure(Call<BookingResponse> call, Throwable t) {
                Log.d("BookingRepositoryBookingDetails", "onFailure: " + t.getMessage());
            }
        });
    }

    public void rateDriver(int driverId, double rating) {
//        TODO: Add API call to rate driver
    }
}
