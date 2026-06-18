package com.hotel.reservation.service;

import com.hotel.reservation.model.Booking;
import com.hotel.reservation.util.FileHandler;
import com.hotel.reservation.model.Room;

import java.util.ArrayList;

public class BookingService {

    private RoomService roomService = new RoomService();
    private UserService userService = new UserService();


    public void createBooking(Booking booking) {

        boolean roomAvailable = false;
        System.out.println("Requested Room: " + booking.getRoomId());

        for (var room : roomService.getRooms()) {

            System.out.println(
                    "RoomID=" + room.getRoomId()
                            + " Available=" + room.isAvailable()
            );

            if (room.getRoomId().equals(booking.getRoomId())
                    && room.isAvailable()) {

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
        String cancelledRoomId = null;

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] data = line.split(",");
            if (data[0].equals(bookingId)) {
                cancelledRoomId = data[2]; // grab the roomId before removing
            } else {
                updatedLines.add(line);
            }
        }

        FileHandler.overwriteFile("data/bookings.txt", updatedLines);

        // Set the room back to available
        if (cancelledRoomId != null) {
            for (Room room : roomService.getRooms()) {
                if (room.getRoomId().equals(cancelledRoomId)) {
                    room.setAvailable(true);
                    roomService.updateRoom(room.getRoomId(), room);
                    break;
                }
            }
        }
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

