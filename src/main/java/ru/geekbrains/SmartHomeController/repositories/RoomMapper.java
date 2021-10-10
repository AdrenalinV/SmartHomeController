package ru.geekbrains.SmartHomeController.repositories;

import ru.geekbrains.SmartHomeController.entities.DeviceSensor;
import ru.geekbrains.SmartHomeController.entities.Room;
import ru.geekbrains.SmartHomeController.services.DataSource;
import ru.geekbrains.SmartHomeController.services.Registry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomMapper {
    private final Connection con;

    public RoomMapper() throws SQLException {
        this.con = DataSource.getConnection();
    }

    public Room findById(Long id) throws SQLException,IllegalArgumentException {
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
        Room room = new Room();
        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                room.setId(rs.getLong(1));
                room.setName(rs.getString(2));

            }
        }
        statement_ds.setLong(1,room.getId());
        try (ResultSet rs = statement_ds.executeQuery()) {
            while (rs.next()) {
                room.getDeviceSensors().add(Registry.getInstance().getDeviceSensorMapper().findById(rs.getLong(1)));
            }
        }
        statement_de.setLong(1,room.getId());
        try (ResultSet rs = statement_de.executeQuery()) {
            while (rs.next()) {
                room.getDeviceExecutors().add(Registry.getInstance().getDeviceExecutorMapper().findById(rs.getLong(1)));
            }
        }
        return room;
    }

        public void insert (Room room) throws SQLException, IllegalArgumentException {
            PreparedStatement statement_ds = con.prepareStatement(
                    "INSERT INTO rooms (name) VALUES (?)"
            );
            statement_ds.setString(1, room.getName());
            statement_ds.executeUpdate();
        }

        public void update (Room room) throws SQLException {

        }

        public void delete (Room room) throws SQLException {
            PreparedStatement statement = con.prepareStatement(
                    "DELETE rooms WHERE id=?"
            );
            for (DeviceSensor ds : room.getDeviceSensors()) {
                Registry.getInstance().getDeviceSensorMapper().delete(ds);
            }
            statement.setLong(1, room.getId());
            statement.executeUpdate();
        }

    }
