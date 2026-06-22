package com.hotel.reservation.controller;

import com.hotel.reservation.model.User;
import com.hotel.reservation.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    private AdminService adminService = new AdminService();

    @GetMapping("/admin")
    public String adminPage(HttpSession session, Model model) {
        User user = SessionHelper.getLoggedInUser(session);
        if (user == null || !user.getRole().equals("Admin")) {
            return "redirect:/login";
        }
        model.addAttribute("users", adminService.getUsers());
        model.addAttribute("rooms", adminService.getRooms());
        model.addAttribute("bookings", adminService.getBookings());
        return "admin";
    }

    @PostMapping("/admin/users/delete")
    public String deleteUser(@RequestParam String userId) {
        adminService.deleteUser(userId);
        return "redirect:/admin";
    }

    @PostMapping("/admin/rooms/add")
    public String addRoom(@RequestParam String roomId,
                          @RequestParam String type,
                          @RequestParam double price) {
        adminService.addRoom(roomId, type, price);
        return "redirect:/admin";
    }

    @PostMapping("/admin/rooms/update")
    public String updateRoom(@RequestParam String roomId,
                             @RequestParam double price,
                             @RequestParam boolean available) {
        adminService.updateRoomDetails(roomId, price, available);
        return "redirect:/admin";
    }

    // delete here below if issue
    @PostMapping("/admin/rooms/delete")
    public String deleteRoom(@RequestParam String roomId) {
        adminService.deleteRoom(roomId);
        return "redirect:/admin";
    }

}