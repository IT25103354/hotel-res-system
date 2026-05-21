package com.hotel.reservation;

import com.hotel.reservation.controller.BookingController;
import com.hotel.reservation.model.Booking;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        BookingController bookingController = new BookingController();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Hotel System ---");
            System.out.println("1. Create Booking");
            System.out.println("2. View Bookings");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {

                System.out.print("Enter Booking ID: ");
                String bookingId = scanner.nextLine();

                System.out.print("Enter User ID: ");
                String userId = scanner.nextLine();

                System.out.print("Enter Room ID: ");
                String roomId = scanner.nextLine();

                System.out.print("Enter Date: ");
                String date = scanner.nextLine();

                Booking booking = new Booking(
                        bookingId, userId, roomId, date, "ACTIVE"
                );

                bookingController.createBooking(booking);

            } else if (choice == 2) {

                for (Booking b : bookingController.getBookings()) {
                    System.out.println(
                            b.getBookingId() + " - " +
                                    b.getUserId() + " - " +
                                    b.getRoomId()
                    );
                }

            } else if (choice == 3) {

                System.out.print("Enter Booking ID to cancel: ");
                String id = scanner.nextLine();

                bookingController.cancelBooking(id);

            } else {
                break;
            }
        }
    }
}