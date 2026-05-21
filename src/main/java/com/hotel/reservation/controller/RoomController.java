package com.hotel.reservation.controller;

import com.hotel.reservation.model.Room;
import com.hotel.reservation.service.RoomService;

import java.util.ArrayList;
import java.util.Scanner;

public class RoomController {

    private RoomService roomService = new RoomService();
    private Scanner scanner = new Scanner(System.in);


    public void addRoom() {

        System.out.println("Enter Room ID:");
        String roomId = scanner.nextLine();

        System.out.println("Enter Room Type:");
        String type = scanner.nextLine();

        System.out.println("Enter Room Price:");
        double price = scanner.nextDouble();

        System.out.println("Is Room Available? (true/false):");
        boolean available = scanner.nextBoolean();
        scanner.nextLine();

        Room room = new Room(roomId, type, price, available);

        roomService.addRoom(room);

        System.out.println("Room added successfully.");
    }

    public void viewRooms() {

        ArrayList<Room> rooms = roomService.getRooms();

        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
            return;
        }

        for (Room room : rooms) {

            System.out.println("---------------------------");
            System.out.println("Room ID: " + room.getRoomId());
            System.out.println("Type: " + room.getType());
            System.out.println("Price: " + room.getPrice());
            System.out.println("Available: " + room.isAvailable());
        }
    }

    public void updateRoom() {

        System.out.println("Enter Room ID to update:");
        String roomId = scanner.nextLine();

        System.out.println("Enter New Room Type:");
        String type = scanner.nextLine();

        System.out.println("Enter New Price:");
        double price = scanner.nextDouble();

        System.out.println("Is Room Available? (true/false):");
        boolean available = scanner.nextBoolean();
        scanner.nextLine();

        Room updatedRoom = new Room(roomId, type, price, available);

        roomService.updateRoom(roomId, updatedRoom);

        System.out.println("Room updated successfully.");
    }

    public void deleteRoom() {

        System.out.println("Enter Room ID to delete:");
        String roomId = scanner.nextLine();

        roomService.deleteRoom(roomId);

        System.out.println("Room deleted successfully.");
    }
}
