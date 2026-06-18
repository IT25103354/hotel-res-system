package com.hotel.reservation.controller;

import com.hotel.reservation.model.Room;
import com.hotel.reservation.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RoomController {

    private RoomService roomService = new RoomService();

    @GetMapping("/rooms")
    public String viewRooms(Model model) {
        model.addAttribute("rooms", roomService.getRooms());
        return "rooms";
    }

    @PostMapping("/rooms/add")
    public String addRoom(@ModelAttribute Room room) {
        roomService.addRoom(room);
        return "redirect:/rooms";
    }

    @PostMapping("/rooms/update")
    public String updateRoom(@RequestParam String roomId,
                             @ModelAttribute Room room) {
        roomService.updateRoom(roomId, room);
        return "redirect:/rooms";
    }

    @PostMapping("/rooms/delete")
    public String deleteRoom(@RequestParam String roomId) {
        roomService.deleteRoom(roomId);
        return "redirect:/rooms";
    }
}