package com.hotel.reservation.service;

import com.hotel.reservation.model.Booking;
import com.hotel.reservation.util.FileHandler;
import com.hotel.reservation.service.RoomService;
import com.hotel.reservation.service.UserService;

import java.util.ArrayList;

public class BookingService {

    private RoomService roomService = new RoomService();
    private UserService userService = new UserService();

    public void createBooking(Booking booking) {


        boolean userExists = false;
        for (var user : userService.getUsers()) {
            if (user.getUserId().equals(booking.getUserId())) {
                userExists = true;
                break;
            }
        }

        if (!userExists) {
            System.out.println("Booking failed: User does not exist.");
            return;
        }


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

        System.out.println("Booking created successfully!");
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

