package com.example.spaceowner.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.spaceowner.model.data.booking.Booking;
import com.example.spaceowner.model.repositories.BookingRepository;

import java.util.ArrayList;
import java.util.List;

public class BookingViewModel extends ViewModel {
    private MutableLiveData<Booking> currentBooking;
    private MutableLiveData<List<Booking>> activeBookings, pastBookings, upcomingBookings;
    private BookingRepository bookingRepository;
    public BookingViewModel(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
        currentBooking = new MutableLiveData<>(new Booking());
        activeBookings = new MutableLiveData<>(new ArrayList<>());
        pastBookings = new MutableLiveData<>(new ArrayList<>());
        upcomingBookings = new MutableLiveData<>(new ArrayList<>());
    }

    public void getBookingDetails(int bookingId){
        bookingRepository.getBookingDetails(bookingId, currentBooking);
    }

    public void fetchActiveBookings(){
//        TODO: Add API call to get active bookings
//        bookingRepository.getActiveBookings(spaceId, activeBookings);
    }

    public void fetchPastBookings(){
//        TODO: Add API call to get past bookings
//        bookingRepository.getPastBookings(spaceId, pastBookings);
    }

    public void fetchUpcomingBookings(){
//        TODO: Add API call to get upcoming bookings
//        bookingRepository.getUpcomingBookings(spaceId, upcomingBookings);
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
    public MutableLiveData<List<Booking>> getUpcomingBookings() { return upcomingBookings; }
}
