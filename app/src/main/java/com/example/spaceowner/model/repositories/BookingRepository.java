package com.example.spaceowner.model.repositories;

public class BookingRepository {
    private static BookingRepository bookingRepository;
    private BookingRepository(){}
    public static BookingRepository getInstance(){
        if(bookingRepository == null) bookingRepository = new BookingRepository();
        return bookingRepository;
    }
}
