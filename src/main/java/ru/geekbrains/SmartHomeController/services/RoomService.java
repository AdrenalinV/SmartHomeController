package ru.geekbrains.SmartHomeController.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.SmartHomeController.entities.Room;
import ru.geekbrains.SmartHomeController.repositories.RoomMapper;

import java.sql.SQLException;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomMapper roomMapper;

    public Room findRoomByID(Long id) {
        try {
            return roomMapper.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }return null;
    }

    public Long addRoom(Room room) {
        try {
            roomMapper.insert(room);
            return roomMapper.getIdByName(room.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }return -1L;
    }

    public void deleteRoom(Long id){
        Room room=findRoomByID(id);
        try {
            roomMapper.delete(room);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRoom(Room room){
        try {
            roomMapper.update(room);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Room> getRoomAll() {
        List<Room> rooms = null;
        try {
             rooms=roomMapper.getRooms();
        } catch (SQLException e) {
            e.printStackTrace();
        }return rooms;
    }

}
