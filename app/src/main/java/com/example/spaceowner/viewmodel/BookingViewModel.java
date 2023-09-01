package com.example.spaceowner.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.spaceowner.model.repositories.BookingRepository;

public class BookingViewModel extends ViewModel {
    private BookingRepository bookingRepository;
    public BookingViewModel(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
}
