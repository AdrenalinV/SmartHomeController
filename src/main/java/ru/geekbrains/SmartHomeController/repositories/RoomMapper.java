package ru.geekbrains.SmartHomeController.repositories;

import org.springframework.stereotype.Repository;
import ru.geekbrains.SmartHomeController.entities.DeviceSensor;
import ru.geekbrains.SmartHomeController.entities.Room;
import ru.geekbrains.SmartHomeController.services.DataSource;
import ru.geekbrains.SmartHomeController.services.Registry;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class RoomMapper {
    private final Connection con;
    private Map<Long, Room> identityMap = new HashMap<>();

    public RoomMapper() throws SQLException {
        this.con = DataSource.getConnection();
    }

    public List<Room> getRooms() throws SQLException {
        Statement statement=con.createStatement( );
        List<Long> ids = new ArrayList<>();
        try(ResultSet rs = statement.executeQuery("SELECT id FROM rooms ORDER BY id")){
            while(rs.next()){
                ids.add(rs.getLong(1));
            }
        }
        List<Room> rooms = new ArrayList<>();
        ids.stream().forEach((id)-> {
            try {
                rooms.add(findById(id));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return rooms;

    }

    public Room findById(Long id) throws SQLException, IllegalArgumentException {
        Room room = identityMap.get(id);
        if (room == null) {

            PreparedStatement statement = con.prepareStatement(
                    "SELECT r.id, r.name FROM ROOMS as r WHERE r.id=?"
            );
            PreparedStatement statement_ds = con.prepareStatement(
                    "SELECT id FROM devices_sensor WHERE room_id=?"
            );
            PreparedStatement statement_de = con.prepareStatement(
                    "SELECT id FROM devices_executor WHERE room_id=?"
            );
            statement.setLong(1, id);
            room = new Room();
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    room.setId(rs.getLong(1));
                    room.setName(rs.getString(2));

                }
            }
            statement_ds.setLong(1, room.getId());
            try (ResultSet rs = statement_ds.executeQuery()) {
                while (rs.next()) {
                    room.getDeviceSensors().add(Registry.getInstance().getDeviceSensorMapper().findById(rs.getLong(1)));
                }
            }
            statement_de.setLong(1, room.getId());
            try (ResultSet rs = statement_de.executeQuery()) {
                while (rs.next()) {
                    room.getDeviceExecutors().add(Registry.getInstance().getDeviceExecutorMapper().findById(rs.getLong(1)));
                }
            }
            identityMap.put(room.getId(), room);
            return room;
        } else return room;
    }

    public void insert(Room room) throws SQLException, IllegalArgumentException {
        PreparedStatement statement = con.prepareStatement(
                "INSERT INTO rooms (name) VALUES (?)"
        );
        statement.setString(1, room.getName());
        statement.executeUpdate();
    }

    public void update(Room room) throws SQLException {

    }

    public void delete(Room room) throws SQLException {
        PreparedStatement statement = con.prepareStatement(
                "DELETE rooms WHERE id=?"
        );
        for (DeviceSensor ds : room.getDeviceSensors()) {
            Registry.getInstance().getDeviceSensorMapper().delete(ds);
        }
        statement.setLong(1, room.getId());
        statement.executeUpdate();
        identityMap.remove(room.getId());
    }

    public Long getIdByName(String name) throws SQLException {
        PreparedStatement statement = con.prepareStatement(
                "SELECT id FROM rooms WHERE name=?"
        );
        statement.setString(1,name);
        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                return rs.getLong(1);
            }
        }
        throw new IllegalArgumentException(name);
    }

}
