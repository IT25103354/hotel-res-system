package com.hotel.reservation.controller;

import com.hotel.reservation.service.RoomService;
import com.hotel.reservation.model.Booking;
import com.hotel.reservation.model.User;
import com.hotel.reservation.service.BookingService;
import com.hotel.reservation.controller.SessionHelper;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookingController {

    private BookingService bookingService = new BookingService();

    @GetMapping("/booking")
    public String bookingPage(HttpSession session, Model model) {
        User user = SessionHelper.getLoggedInUser(session);
        if (user == null) {
            return "redirect:/login";
        }
        RoomService roomService = new RoomService();
        model.addAttribute("availableRooms", roomService.getAvailableRooms());
        model.addAttribute("currentUserId", user.getUserId());
        model.addAttribute("bookings", bookingService.getBookings());
        return "bookings";
    }

    @PostMapping("/booking/create")
    public String createBooking(@ModelAttribute Booking booking, HttpSession session) {
        User user = SessionHelper.getLoggedInUser(session);
        if (user == null) {
            return "redirect:/login";
        }
        bookingService.createBooking(booking);
        return "redirect:/booking";
    }

    @PostMapping("/booking/cancel")
    public String cancelBooking(@RequestParam String bookingId, HttpSession session) {
        User user = SessionHelper.getLoggedInUser(session);
        if (user == null) {
            return "redirect:/login";
        }
        bookingService.cancelBooking(bookingId);
        return "redirect:/booking";
    }
}