package com.hotel.reservation.controller;

import com.hotel.reservation.model.Booking;
import com.hotel.reservation.service.BookingService;

import java.util.ArrayList;

public class BookingController {

    private BookingService bookingService = new BookingService();


    public void createBooking(Booking booking) {
        bookingService.createBooking(booking);
    }


    public ArrayList<Booking> getBookings() {
        return bookingService.getBookings();
    }


    public void cancelBooking(String bookingId) {
        bookingService.cancelBooking(bookingId);
    }
}
