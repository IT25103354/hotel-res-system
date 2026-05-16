package com.hotel.reservation.model;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminService {


    public boolean isAdmin(String userId) {
        return false;
    }

    //  USER MANAGEMENT ACTIONS (Admin Permissions)

    public void viewAllUsers() {
    }

    public void deleteUser(String userId) {
        System.out.println("Admin Feature: Deleting user ID: " + userId);
    }

    //  ROOM MANAGEMENT ACTIONS (Admin Permissions)

    public void addRoom(String roomId, String roomType, double pricePerNight) {
        System.out.println("Admin Feature: Adding new room " + roomId + " to rooms.txt...");
    }

    public void updateRoomDetails(String roomId, double newPrice, boolean isAvailable) {
        System.out.println("Admin Feature: Updating room ID: " + roomId);
    }

    //  SYSTEM BOOKING CONTROL (Admin Permissions)

    public void viewAllBookings() {
        System.out.println("Admin Feature: Fetching all active bookings from bookings.txt...");
    }
}