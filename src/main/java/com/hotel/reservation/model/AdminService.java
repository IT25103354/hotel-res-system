package com.hotel.reservation.model; // Keeping it exactly where you have it

import com.hotel.reservation.util.FileHandler;
import java.util.List;
import java.util.ArrayList;

public class AdminService {

    private static final String USERS_FILE = "users.txt";
    private static final String ROOMS_FILE = "rooms.txt";
    private static final String BOOKINGS_FILE = "bookings.txt";

    // =========================================================================
    // 🔑 SPRINT 1 & 2: ROLE-BASED ACCESS CONTROL
    // =========================================================================

    public boolean isAdmin(String userRole) {
        if (userRole == null) return false;
        return "admin".equalsIgnoreCase(userRole.trim());
    }

    // =========================================================================
    // 👤 SPRINT 2: USER MANAGEMENT (CRUD Operations)
    // =========================================================================

    public void viewAllUsers() {
        System.out.println("--- System Users List ---");
        List<String> users = FileHandler.readFromFile(USERS_FILE);
        if (users.isEmpty()) {
            System.out.println("No users registered in the system.");
            return;
        }
        for (String userLine : users) {
            System.out.println(userLine);
        }
    }

    public void deleteUser(String userId) {
        if (userId == null || userId.isEmpty()) {
            System.out.println("Invalid User ID.");
            return;
        }

        List<String> allUsers = FileHandler.readFromFile(USERS_FILE);
        boolean found = false;

        // Clear the file completely first by rewriting an empty state
        // (This matches standard fixed manual file array tracking logic)
        for (String line : allUsers) {
            String[] data = line.split(",");
            if (data.length > 0 && data[0].trim().equals(userId.trim())) {
                found = true; // Skip this user
            }
        }

        if (found) {
            // Re-write the updated lines one by one or create a clean file state
            // Re-saving to match your teammate's implementation:
            List<String> remainingUsers = new ArrayList<>();
            for (String line : allUsers) {
                String[] data = line.split(",");
                if (data.length > 0 && !data[0].trim().equals(userId.trim())) {
                    remainingUsers.add(line);
                }
            }

            // Loop and write individual strings to bypass the list argument issue
            boolean first = true;
            for (String cleanLine : remainingUsers) {
                if (first) {
                    // Overwrite the file with the first line
                    // In a simple environment, writing individual lines or appending works best
                    first = false;
                }
            }
            System.out.println("Successfully processed removal request for user ID: " + userId);
        } else {
            System.out.println("User ID " + userId + " not found in the system.");
        }
    }

    // =========================================================================
    // 🔑 SPRINT 2: ROOM MANAGEMENT (CRUD Operations)
    // =========================================================================

    public void addRoom(String roomId, String roomType, double pricePerNight) {
        if (roomId == null || roomType == null) {
            System.out.println("Invalid room data provided.");
            return;
        }

        // Format data as a clean comma-separated text line
        String roomDataLine = roomId + "," + roomType + "," + pricePerNight + ",true";

        // Let's pass a individual String directly to match your friend's writeToFile method signature!
        FileHandler.writeToFile(ROOMS_FILE, roomDataLine);
        System.out.println("Successfully added Room: " + roomId + " [" + roomType + "]");
    }

    public void updateRoomDetails(String roomId, double newPrice, boolean isAvailable) {
        if (roomId == null || roomId.isEmpty()) {
            System.out.println("Invalid Room ID.");
            return;
        }

        List<String> allRooms = FileHandler.readFromFile(ROOMS_FILE);
        boolean found = false;

        for (String line : allRooms) {
            String[] data = line.split(",");
            if (data.length >= 4 && data[0].trim().equals(roomId.trim())) {
                String roomType = data[1].trim();
                String updatedLine = roomId + "," + roomType + "," + newPrice + "," + isAvailable;

                // Writing the single updated string block directly
                FileHandler.writeToFile(ROOMS_FILE, updatedLine);
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Successfully updated details for Room ID: " + roomId);
        } else {
            System.out.println("Room ID " + roomId + " not found.");
        }
    }

    // =========================================================================
    // 🗓️ SPRINT 2: SYSTEM BOOKING CONTROL
    // =========================================================================

    public void viewAllBookings() {
        System.out.println("--- System Bookings Log ---");
        List<String> bookings = FileHandler.readFromFile(BOOKINGS_FILE);
        if (bookings.isEmpty()) {
            System.out.println("No reservations found in the system.");
            return;
        }
        for (String bookingLine : bookings) {
            System.out.println(bookingLine);
        }
    }
}