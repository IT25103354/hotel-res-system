package com.hotel.reservation.controller;

import com.hotel.reservation.service.AdminService;
import com.hotel.reservation.util.FileHandler;
import java.util.ArrayList;

public class AdminController {

    private final AdminService adminService;

    public AdminController() {
        this.adminService = new AdminService();
    }


    // Called by the UI layer to read lines and print all users
    public void viewAllUsers() {
        adminService.viewAllUsers();
    }

    // Called by the UI layer to delete a specific user account line
    public void deleteUser(String userId) {
        adminService.deleteUser(userId);
    }


    // Called by the UI layer to print raw room entries out of the text database
    public void viewAllRooms() {
        System.out.println("--- System Rooms List ---");
        ArrayList<String> rooms = FileHandler.readFromFile("data/rooms.txt");
        if (rooms.isEmpty()) {
            System.out.println("No rooms found in the system.");
            return;
        }
        for (int i = 0; i < rooms.size(); i++) {
            System.out.println(rooms.get(i));
        }
    }

    // Called by the UI layer to pass variables straight down to add a room line
    public void addRoom(String roomId, String roomType, double pricePerNight) {
        adminService.addRoom(roomId, roomType, pricePerNight);
    }

    // Called by the UI layer to pass updated variables down to modify a room record
    public void updateRoomDetails(String roomId, double newPrice, boolean isAvailable) {
        adminService.updateRoomDetails(roomId, newPrice, isAvailable);
    }


    // Called by the UI layer to pull log lines and print all system bookings
    public void viewAllBookings() {
        adminService.viewAllBookings();
    }
}