package com.hotel.reservation.service;
import com.hotel.reservation.model.Room;
import com.hotel.reservation.util.FileHandler;
import java.util.ArrayList;

public class RoomService {

    private final String FILE_NAME = "data/rooms.txt";
    // Add room
    public void addRoom(Room room) {

        String data = room.getRoomId() + "," +
                room.getType() + "," +
                room.getPrice() + "," +
                room.isAvailable();

        FileHandler.writeToFile(FILE_NAME, data);
    }

    // Get all rooms
    public ArrayList<Room> getRooms() {

        ArrayList<String> lines = FileHandler.readFromFile(FILE_NAME);

        ArrayList<Room> rooms = new ArrayList<>();

        for (String line : lines) {

            String[] data = line.split(",");

            Room room = new Room(
                    data[0],
                    data[1],
                    Double.parseDouble(data[2]),
                    Boolean.parseBoolean(data[3])
            );

            rooms.add(room);
        }

        return rooms;
    }

    // Update room
    public void updateRoom(String roomId, Room updatedRoom) {

        ArrayList<String> lines = FileHandler.readFromFile(FILE_NAME);

        ArrayList<String> updatedLines = new ArrayList<>();

        for (String line : lines) {

            String[] data = line.split(",");

            if (data[0].equals(roomId)) {

                String updatedData =
                        updatedRoom.getRoomId() + "," +
                                updatedRoom.getType() + "," +
                                updatedRoom.getPrice() + "," +
                                updatedRoom.isAvailable();

                updatedLines.add(updatedData);

            } else {
                updatedLines.add(line);
            }
        }

        FileHandler.overwriteFile(FILE_NAME, updatedLines);
    }

    // Delete room
    public void deleteRoom(String roomId) {

        ArrayList<String> lines = FileHandler.readFromFile(FILE_NAME);

        ArrayList<String> updatedLines = new ArrayList<>();

        for (String line : lines) {

            String[] data = line.split(",");

            if (!data[0].equals(roomId)) {
                updatedLines.add(line);
            }
        }

        FileHandler.overwriteFile(FILE_NAME, updatedLines);
    }
}