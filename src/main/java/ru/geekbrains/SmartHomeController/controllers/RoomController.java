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

        List<Room> rooms = roomService.getRoomAll();
        return rooms;

    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Long createRoom(@RequestBody Room room) {

        return roomService.addRoom(room);


    }

    @PutMapping()
    public void updateRoom(Room room) {

        roomService.updateRoom(room);

    }

    @DeleteMapping("/{id}")
    public void deleteRoomByID(@PathVariable Long id) {

        roomService.deleteRoom(id);

    }


}
