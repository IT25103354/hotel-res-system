package com.hotel.reservation.controller;

import com.hotel.reservation.model.User;
import com.hotel.reservation.service.UserService;

public class UserController {

    // The Controller holds the service so it can pass data to it
    private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }


    public boolean registerUser(String userId, String name, String email, String password, String role) {
        // Create the model object
        User newUser = new User(userId, name, email, password, role);

        // Return the boolean result (true/false) to the UI developer
        return userService.registerUser(newUser);
    }


    public User loginUser(String email, String password) {
        // Return the User object (or null) to the UI developer
        return userService.loginUser(email, password);
    }
}