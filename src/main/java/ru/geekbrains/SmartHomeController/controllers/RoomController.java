package ru.geekbrains.SmartHomeController.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.SmartHomeController.entities.Room;
import ru.geekbrains.SmartHomeController.services.RoomService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping()
    public List<Room> getRooms() {
        try {
            List<Room> rooms = roomService.getRoomAll();
            return rooms;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Long createRoom(@RequestBody Room room) {
        try {
            return roomService.addRoom(room);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1L;
    }

    @PutMapping()
    public void updateRoom(Room room) {
        try {
            roomService.updateRoom(room);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteRoomByID(@PathVariable Long id) {
        try {
            roomService.deleteRoom(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
