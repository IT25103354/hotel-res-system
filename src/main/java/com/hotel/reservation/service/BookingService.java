package com.hotel.reservation.service;

import com.hotel.reservation.model.Booking;
import com.hotel.reservation.util.FileHandler;

import java.util.ArrayList;

public class BookingService {

    public void createBooking(Booking booking) {
        String data = booking.getBookingId() + "," +
                booking.getUserId() + "," +
                booking.getRoomId() + "," +
                booking.getDate() + "," +
                booking.getStatus();

        FileHandler.writeToFile("data/bookings.txt", data);
    }

    public ArrayList<Booking> getBookings() {

        ArrayList<Booking> bookingList = new ArrayList<>();
        ArrayList<String> lines = FileHandler.readFromFile("data/bookings.txt");

        for (String line : lines) {
            String[] data = line.split(",");

            Booking booking = new Booking(
                    data[0], // bookingId
                    data[1], // userId
                    data[2], // roomId
                    data[3], // date
                    data[4]  // status
            );

            bookingList.add(booking);
        }

        return bookingList;
    }

    public void cancelBooking(String bookingId) {

        ArrayList<String> lines = FileHandler.readFromFile("data/bookings.txt");
        ArrayList<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            String[] data = line.split(",");

            if (!data[0].equals(bookingId)) {
                updatedLines.add(line);
            }
        }

        FileHandler.overwriteFile("data/bookings.txt", updatedLines);
    }

    public ArrayList<Booking> getBookingsByUser(String userId) {
        return new ArrayList<>();
    }
}