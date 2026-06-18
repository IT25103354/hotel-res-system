package com.hotel.reservation.controller;

import com.hotel.reservation.model.Booking;
import com.hotel.reservation.service.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookingController {

    private BookingService bookingService = new BookingService();

    @GetMapping("/booking")
    public String bookingPage(Model model) {
        model.addAttribute("bookings", bookingService.getBookings());
        return "booking";
    }

    @PostMapping("/booking/create")
    public String createBooking(@ModelAttribute Booking booking) {
        bookingService.createBooking(booking);
        return "redirect:/booking";
    }

    @PostMapping("/booking/cancel")
    public String cancelBooking(@RequestParam String bookingId) {
        bookingService.cancelBooking(bookingId);
        return "redirect:/booking";
    }
}