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

    public Room findRoomByID(Long id) throws SQLException {
        return roomMapper.findById(id);
    }

    public Long addRoom(Room room) throws SQLException {
        roomMapper.insert(room);
        return roomMapper.getIdByName(room.getName());
    }

    public void deleteRoom(Long id) throws SQLException {
        Room room=findRoomByID(id);
        roomMapper.delete(room);
    }

    public void updateRoom(Room room) throws SQLException {
        roomMapper.update(room);
    }

    public List<Room> getRoomAll() throws SQLException {
        return roomMapper.getRooms();
    }

}
