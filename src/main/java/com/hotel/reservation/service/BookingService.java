package com.hotel.reservation.service;

import com.hotel.reservation.model.Booking;
import com.hotel.reservation.util.FileHandler;

import java.util.ArrayList;

public class BookingService {

    private RoomService roomService = new RoomService();
    private UserService userService = new UserService();


    public void createBooking(Booking booking) {

        boolean roomAvailable = false;
        for (var room : roomService.getRooms()) {
            if (room.getRoomId().equals(booking.getRoomId()) && room.isAvailable()) {
                roomAvailable = true;
                break;
            }
        }

        if (!roomAvailable) {
            System.out.println("Booking failed: Room not available.");
            return;
        }

        String data = booking.getBookingId() + "," +
                booking.getUserId() + "," +
                booking.getRoomId() + "," +
                booking.getDate() + "," +
                booking.getStatus();

        FileHandler.writeToFile("data/bookings.txt", data);

        // Mark room as unavailable
        for (var room : roomService.getRooms()) {
            if (room.getRoomId().equals(booking.getRoomId())) {
                room.setAvailable(false);
                roomService.updateRoom(room.getRoomId(), room);
                break;
            }
        }

        System.out.println("Booking created successfully!");
    }


    public ArrayList<Booking> getBookings() {

        ArrayList<Booking> bookingList = new ArrayList<>();
        ArrayList<String> lines = FileHandler.readFromFile("data/bookings.txt");

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            String[] data = line.split(",");

            if (data.length >= 5) {
                Booking booking = new Booking(
                        data[0],
                        data[1],
                        data[2],
                        data[3],
                        data[4]
                );
                bookingList.add(booking);
            }
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

        ArrayList<Booking> allBookings = getBookings();
        ArrayList<Booking> userBookings = new ArrayList<>();

        for (Booking booking : allBookings) {
            if (booking.getUserId().equals(userId)) {
                userBookings.add(booking);
            }
        }

        return userBookings;
    }
}

