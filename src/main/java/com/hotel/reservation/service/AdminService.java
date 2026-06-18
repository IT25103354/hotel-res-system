package com.hotel.reservation.service;

import com.hotel.reservation.model.User;
import com.hotel.reservation.model.Room;
import com.hotel.reservation.model.Booking;
import com.hotel.reservation.util.FileHandler;

import java.util.ArrayList;
import java.util.List;

public class AdminService {

    private static final String USERS_FILE = "data/users.txt";
    private static final String ROOMS_FILE = "data/rooms.txt";
    private static final String BOOKINGS_FILE = "data/bookings.txt";

    //  GET USER
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        ArrayList<String> lines = FileHandler.readFromFile(USERS_FILE);

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            String[] data = line.split(",");

            if (data.length >= 5) {
                userList.add(new User(
                        data[0], data[1], data[2], data[3], data[4]
                ));
            }
        }
        return userList;
    }

    //  GET ROOMS
    public List<Room> getRooms() {
        List<Room> roomList = new ArrayList<>();
        ArrayList<String> lines = FileHandler.readFromFile(ROOMS_FILE);

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            String[] data = line.split(",");

            if (data.length >= 4) {
                roomList.add(new Room(
                        data[0],
                        data[1],
                        Double.parseDouble(data[2]),
                        Boolean.parseBoolean(data[3])
                ));
            }
        }
        return roomList;
    }

    //  GET BOOKINGS
    public List<Booking> getBookings() {
        List<Booking> bookingList = new ArrayList<>();
        ArrayList<String> lines = FileHandler.readFromFile(BOOKINGS_FILE);

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            String[] data = line.split(",");

            if (data.length >= 5) {
                bookingList.add(new Booking(
                        data[0], // bookingId
                        data[1], // userId
                        data[2], // roomId
                        data[3], // date
                        data[4]  // status
                ));
            }
        }
        return bookingList;
    }

    //  DELETE USER
    public void deleteUser(String userId) {
        ArrayList<String> allUsers = FileHandler.readFromFile(USERS_FILE);
        ArrayList<String> updated = new ArrayList<>();

        for (String line : allUsers) {
            if (line.trim().isEmpty()) continue;

            String[] data = line.split(",");
            if (!data[0].equals(userId)) {
                updated.add(line);
            }
        }

        FileHandler.overwriteFile(USERS_FILE, updated);
    }

    //  ADD ROOM
    public void addRoom(String roomId, String type, double price) {
        String room = roomId + "," + type + "," + price + ",true";
        FileHandler.writeToFile(ROOMS_FILE, room);
    }

    //  UPDATE ROOM
    public void updateRoomDetails(String roomId, double price, boolean available) {
        ArrayList<String> rooms = FileHandler.readFromFile(ROOMS_FILE);

        for (int i = 0; i < rooms.size(); i++) {
            String[] data = rooms.get(i).split(",");

            if (data[0].equals(roomId)) {
                rooms.set(i, roomId + "," + data[1] + "," + price + "," + available);
                break;
            }
        }

        FileHandler.overwriteFile(ROOMS_FILE, rooms);
    }
}