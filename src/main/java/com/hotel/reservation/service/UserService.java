package com.hotel.reservation.service;

import com.hotel.reservation.model.User;
import com.hotel.reservation.util.FileHandler;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final String USER_FILE = "data/users.txt";

    // CRUD: CREATE
    public boolean registerUser(User newUser) {
        ArrayList<String> lines = FileHandler.readFromFile(USER_FILE);
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            String[] data = line.split(",");

            // Check if email (index 2) already exists
            if (data[2].equalsIgnoreCase(newUser.getEmail())) {
                System.out.println("Registration failed: Email already registered.");
                return false;
            }
        }

        // Save the new user record onto the text database
        FileHandler.writeToFile(USER_FILE, newUser.toString());
        System.out.println("User registered successfully!");
        return true;
    }

    // CRUD: READ
    public User loginUser(String email, String password) {
        ArrayList<String> lines = FileHandler.readFromFile(USER_FILE);

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            String[] data = line.split(",");

            // Match email (index 2) and password (index 3)
            if (data[2].equalsIgnoreCase(email) && data[3].equals(password)) {
                System.out.println("Login successful! Welcome, " + data[1]);
                return new User(data[0], data[1], data[2], data[3], data[4]);
            }
        }

        System.out.println("Login failed: Invalid credentials.");
        return null;
    }

    // CRUD: READ ALL (Sprint 2 Requirement)
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        ArrayList<String> lines = FileHandler.readFromFile(USER_FILE);

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            String[] data = line.split(",");

            if (data.length >= 5) {
                userList.add(new User(data[0], data[1], data[2], data[3], data[4]));
            }
        }
        return userList;
    }

    // CRUD: UPDATE
    public boolean updateUserProfile(String userId, User updatedUserDetails) {
        ArrayList<String> lines = FileHandler.readFromFile(USER_FILE);
        boolean found = false;

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.trim().isEmpty()) continue;

            String[] data = line.split(",");

            if (data[0].equals(userId)) {
                lines.set(i, updatedUserDetails.toString());
                found = true;
                break;
            }
        }

        if (found) {
            FileHandler.overwriteFile(USER_FILE, lines);
            System.out.println("User profile updated successfully.");
            return true;
        }

        System.out.println("Update failed: User ID not found.");
        return false;
    }
}