package com.example.spaceowner.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.spaceowner.model.data.booking.Booking;
import com.example.spaceowner.model.repositories.BookingRepository;

import java.util.ArrayList;
import java.util.List;

public class BookingViewModel extends ViewModel {
    private MutableLiveData<Booking> currentBooking;
    private MutableLiveData<List<Booking>> activeBookings, pastBookings, requestedBookings;
    private BookingRepository bookingRepository;
    public BookingViewModel(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
        currentBooking = new MutableLiveData<>(new Booking());
        activeBookings = new MutableLiveData<>(new ArrayList<>());
        pastBookings = new MutableLiveData<>(new ArrayList<>());
        requestedBookings = new MutableLiveData<>(new ArrayList<>());
    }

    public void getBookingDetails(int bookingId){
        bookingRepository.getBookingDetails(bookingId, currentBooking);
    }

    public void fetchActiveBookings(){
        bookingRepository.getActiveBookings(activeBookings);
    }

    public void fetchPastBookings(){
        bookingRepository.getPastBookings(pastBookings);
    }

    public void fetchRequestedBookings(){
        bookingRepository.getRequestedBookings(requestedBookings);
    }

    public void rateDriver(int bookingId, double rating) {
//        TODO: Add API call to rate driver
        bookingRepository.rateDriver(bookingId, rating);
    }

    public void acceptBooking(int bookingId) {
//        TODO: Add API call to accept booking
//        bookingRepository.acceptBooking(bookingId);
    }

    public MutableLiveData<Booking> getCurrentBooking() { return currentBooking; }
    public MutableLiveData<List<Booking>> getActiveBookings() { return activeBookings; }
    public MutableLiveData<List<Booking>> getPastBookings() { return pastBookings; }
    public MutableLiveData<List<Booking>> getRequestedBookings() { return requestedBookings; }
}
