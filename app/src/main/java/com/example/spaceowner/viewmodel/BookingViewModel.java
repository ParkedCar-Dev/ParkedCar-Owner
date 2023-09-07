package com.example.spaceowner.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.spaceowner.model.data.Booking;
import com.example.spaceowner.model.repositories.BookingRepository;

public class BookingViewModel extends ViewModel {
    private MutableLiveData<Booking> currentBooking = new MutableLiveData<>();
    private BookingRepository bookingRepository;
    public BookingViewModel(BookingRepository bookingRepository) {
        currentBooking = new MutableLiveData<>(new Booking());
        this.bookingRepository = bookingRepository;
    }

    public void getBookingDetails(int bookingId){
        bookingRepository.getBookingDetails(bookingId, currentBooking);
    }

    public MutableLiveData<Booking> getCurrentBooking() {
        return currentBooking;
    }

    public void rateDriver(double rating) {
//        TODO: Add API call to rate driver
//        bookingRepository.rateDriver(currentBooking.getValue().getDriverId(), rating);
    }
}
