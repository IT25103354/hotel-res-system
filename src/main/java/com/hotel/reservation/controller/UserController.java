package com.hotel.reservation.controller;

import com.hotel.reservation.model.User;
import com.hotel.reservation.service.UserService;
import java.util.Scanner;

public class UserController {

    private UserService userService = new UserService();
    private Scanner scanner = new Scanner(System.in);

    public void registerUser() {
        System.out.println("Enter User ID:");
        String userId = scanner.nextLine();

        System.out.println("Enter Name:");
        String name = scanner.nextLine();

        System.out.println("Enter Email:");
        String email = scanner.nextLine();

        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        System.out.println("Enter Role (e.g., Guest/Admin):");
        String role = scanner.nextLine();

        User newUser = new User(userId, name, email, password, role);

        boolean success = userService.registerUser(newUser);

        if (success) {
            System.out.println("User registered successfully.");
        } else {
            System.out.println("Registration failed. User might already exist.");
        }
    }

    public void loginUser() {
        System.out.println("Enter Email:");
        String email = scanner.nextLine();

        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        User user = userService.loginUser(email, password);

        if (user != null) {
            System.out.println("Login successful! Welcome, " + user.getName());
        } else {
            System.out.println("Invalid email or password.");
        }
    }
}