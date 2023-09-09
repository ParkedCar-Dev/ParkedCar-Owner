package com.example.spaceowner.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.spaceowner.model.data.GenericResponse;
import com.example.spaceowner.model.data.booking.Booking;
import com.example.spaceowner.model.repositories.BookingRepository;

import java.util.ArrayList;
import java.util.List;

public class BookingViewModel extends ViewModel {
    private MutableLiveData<Booking> currentBooking;
    private MutableLiveData<List<Booking>> activeBookings, pastBookings, requestedBookings;
    private BookingRepository bookingRepository;
    private MutableLiveData<GenericResponse> acceptDeclineResponse;
    private boolean showCancelled = false;
    public BookingViewModel(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
        currentBooking = new MutableLiveData<>(new Booking());
        activeBookings = new MutableLiveData<>(new ArrayList<>());
        pastBookings = new MutableLiveData<>(new ArrayList<>());
        requestedBookings = new MutableLiveData<>(new ArrayList<>());
        acceptDeclineResponse = new MutableLiveData<>(new GenericResponse());
    }

    public void getBookingDetails(int bookingId){
        bookingRepository.getBookingDetails(bookingId, currentBooking);
    }

    public void fetchActiveBookings(){
        bookingRepository.getActiveBookings(activeBookings);
    }

    public void fetchPastBookings(){
        if(showCancelled)
            bookingRepository.getPastBookingsWithCancelledOnes(pastBookings);
        else
            bookingRepository.getPastBookings(pastBookings);
    }

    public void fetchRequestedBookings(){
        bookingRepository.getRequestedBookings(requestedBookings);
    }

    public void rateDriver(int bookingId, double rating) {
        bookingRepository.rateDriver(bookingId, rating, acceptDeclineResponse);
    }

    public void acceptBooking(int bookingId) {
        bookingRepository.acceptBooking(bookingId, acceptDeclineResponse);
    }

    public void declineBooking(int bookingId) {
        bookingRepository.declineBooking(bookingId, acceptDeclineResponse);
    }

    public MutableLiveData<GenericResponse> getAcceptDeclineResponse() { return acceptDeclineResponse; }
    public MutableLiveData<Booking> getCurrentBooking() { return currentBooking; }
    public MutableLiveData<List<Booking>> getActiveBookings() { return activeBookings; }
    public MutableLiveData<List<Booking>> getPastBookings() { return pastBookings; }
    public MutableLiveData<List<Booking>> getRequestedBookings() { return requestedBookings; }

    public void confirmPayment(int bookingId) {
        bookingRepository.confirmPayment(bookingId, acceptDeclineResponse);
    }

    public void setShowCancelled(boolean isChecked) {
        showCancelled = isChecked;
    }
}
