package com.hotel.reservation.service;

import com.hotel.reservation.util.FileHandler;
import java.util.ArrayList;

public class AdminService {

    // Syncing file paths to match your team's "data/" directory structure
    private static final String USERS_FILE = "data/users.txt";
    private static final String ROOMS_FILE = "data/rooms.txt";
    private static final String BOOKINGS_FILE = "data/bookings.txt";

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
        ArrayList<String> users = FileHandler.readFromFile(USERS_FILE);
        if (users.isEmpty()) {
            System.out.println("No users registered in the system.");
            return;
        }
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }
    }

    public void deleteUser(String userId) {
        if (userId == null || userId.isEmpty()) {
            System.out.println("Invalid User ID.");
            return;
        }

        ArrayList<String> allUsers = FileHandler.readFromFile(USERS_FILE);
        ArrayList<String> remainingUsers = new ArrayList<>();
        boolean found = false;

        // Separate the remaining users from the one being deleted
        for (int i = 0; i < allUsers.size(); i++) {
            String line = allUsers.get(i);
            if (line.trim().isEmpty()) continue;

            String[] data = line.split(",");
            if (data.length > 0 && data[0].trim().equals(userId.trim())) {
                found = true; // Skip this user line (deleting it)
            } else {
                remainingUsers.add(line); // Keep everyone else
            }
        }

        if (found) {
            // FIX: Safely wipe and completely overwrite the file with the clean data
            try {
                java.nio.file.Files.write(
                        java.nio.file.Paths.get(USERS_FILE),
                        new byte[0],
                        java.nio.file.StandardOpenOption.TRUNCATE_EXISTING
                );

                // Write the remaining users back line by line using your FileHandler
                for (int i = 0; i < remainingUsers.size(); i++) {
                    FileHandler.writeToFile(USERS_FILE, remainingUsers.get(i));
                }
                System.out.println("Successfully deleted user ID: " + userId);
            } catch (Exception e) {
                System.out.println("Error wiping file for user deletion.");
            }
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

        // Format data as a clean comma-separated text line (Default availability to true)
        String roomDataLine = roomId + "," + roomType + "," + pricePerNight + ",true";

        FileHandler.writeToFile(ROOMS_FILE, roomDataLine);
        System.out.println("Successfully added Room: " + roomId + " [" + roomType + "]");
    }

    public void updateRoomDetails(String roomId, double newPrice, boolean isAvailable) {
        if (roomId == null || roomId.isEmpty()) {
            System.out.println("Invalid Room ID.");
            return;
        }

        ArrayList<String> allRooms = FileHandler.readFromFile(ROOMS_FILE);
        boolean found = false;

        // Track changes manually in our tracking list
        for (int i = 0; i < allRooms.size(); i++) {
            String line = allRooms.get(i);
            if (line.trim().isEmpty()) continue;

            String[] data = line.split(",");
            if (data.length >= 2 && data[0].trim().equals(roomId.trim())) {
                String roomType = data[1].trim();
                // Replace the element at index i with the updated comma-separated values
                String updatedLine = roomId + "," + roomType + "," + newPrice + "," + isAvailable;
                allRooms.set(i, updatedLine);
                found = true;
                break;
            }
        }

        if (found) {
            // FIX: Wipes old file so appending doesn't double-up the text data layout
            try {
                java.nio.file.Files.write(
                        java.nio.file.Paths.get(ROOMS_FILE),
                        new byte[0],
                        java.nio.file.StandardOpenOption.TRUNCATE_EXISTING
                );

                // Re-write the updated list back into storage line by line safely
                for (int i = 0; i < allRooms.size(); i++) {
                    FileHandler.writeToFile(ROOMS_FILE, allRooms.get(i));
                }
                System.out.println("Successfully updated details for Room ID: " + roomId);
            } catch (Exception e) {
                System.out.println("Error wiping file for room update.");
            }
        } else {
            System.out.println("Room ID " + roomId + " not found.");
        }
    }

    // =========================================================================
    // 🗓️ SPRINT 2: SYSTEM BOOKING CONTROL
    // =========================================================================

    public void viewAllBookings() {
        System.out.println("--- System Bookings Log ---");
        ArrayList<String> bookings = FileHandler.readFromFile(BOOKINGS_FILE);
        if (bookings.isEmpty()) {
            System.out.println("No reservations found in the system.");
            return;
        }
        for (int i = 0; i < bookings.size(); i++) {
            System.out.println(bookings.get(i));
        }
    }
}