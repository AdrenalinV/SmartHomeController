package ru.geekbrains.SmartHomeController.repositories;

import org.springframework.stereotype.Repository;
import ru.geekbrains.SmartHomeController.entities.DeviceSensor;
import ru.geekbrains.SmartHomeController.services.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DeviceSensorMapper {
    private final Connection con;
    private Map<Long, DeviceSensor> identityMap = new HashMap<>();

    public DeviceSensorMapper() throws SQLException {
        this.con = DataSource.getConnection();
    }

    public List<DeviceSensor> getSensorsByRoom_ID(Long room_id) throws SQLException {
        List<DeviceSensor> sensors = new ArrayList();
        PreparedStatement statement = con.prepareStatement(
                "SELECT ds.id, ds.name, ds.type, m.meaning, ds.room_id FROM devices_sensor as ds " +
                        "JOIN meanings as m ON m.sensor_id=ds.id " +
                        "WHERE m.created_at IN (SELECT max(created_at) FROM meanings group by sensor_id) AND ds.room_id=?"
        );
        statement.setLong(1, room_id);
        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                DeviceSensor ds = new DeviceSensor();
                ds.setId(rs.getLong(1));
                ds.setName(rs.getString(2));
                ds.setType(rs.getString(3));
                ds.setMeaning(rs.getDouble(4));
                ds.setRoom_id(rs.getLong(5));
                sensors.add(ds);
            }
        }
        return sensors;
    }

    public DeviceSensor findById(Long id) throws SQLException, IllegalArgumentException {
        DeviceSensor ds = identityMap.get(id);
        if (ds == null) {
            PreparedStatement statement = con.prepareStatement(
                    "SELECT ds.id, ds.name, ds.type, m.meaning, ds.room_id FROM devices_sensor as ds " +
                            "JOIN meanings as m ON m.sensor_id=ds.id " +
                            "WHERE m.created_at=(SELECT max(created_at) FROM meanings WHERE sensor_id=?) AND ds.id=?"
            );
            statement.setLong(1, id);
            statement.setLong(2, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    ds = new DeviceSensor();
                    ds.setId(rs.getLong(1));
                    ds.setName(rs.getString(2));
                    ds.setType(rs.getString(3));
                    ds.setMeaning(rs.getDouble(4));
                    ds.setRoom_id(rs.getLong(5));
                    identityMap.put(ds.getId(), ds);
                    return ds;
                }
            }
            throw new IllegalArgumentException(String.valueOf(id));
        } else return ds;
    }

    public Long insert(DeviceSensor ds) throws SQLException, IllegalArgumentException {
        PreparedStatement statement_ds = con.prepareStatement(
                "INSERT INTO devices_sensor (id, name, type) VALUES (?,?,?)"
        );
        statement_ds.setLong(1, ds.getId());
        statement_ds.setString(2, ds.getName());
        statement_ds.setString(3, ds.getType());
        statement_ds.executeUpdate();
        return ds.getId();

    }

    public void update(DeviceSensor ds) throws SQLException {
        PreparedStatement statement = con.prepareStatement(
                "UPDATE devices_sensor SET name=?, type=?, room_id=? WHERE id=?"
        );
        statement.setString(1, ds.getName());
        statement.setString(2, ds.getType());
        statement.setLong(3, ds.getRoom_id());
        statement.setLong(4, ds.getId());
        statement.executeUpdate();

    }

    public void updateMeaning(DeviceSensor ds) throws SQLException {
        PreparedStatement statement = con.prepareStatement(
                "INSERT INTO meanings (sensor_id,meaning) VALUES (?, ?)"
        );
        Long id = (ds.getId() != null) ? ds.getId() : getID(ds);
        statement.setLong(1, id);
        statement.setDouble(2, ds.getMeaning());
        statement.executeUpdate();
        identityMap.put(ds.getId(), ds);
    }

    public void delete(DeviceSensor ds) throws SQLException {
        PreparedStatement statement_m = con.prepareStatement(
                "DELETE meanings WHERE sensor_id=?"
        );
        PreparedStatement statement_ds = con.prepareStatement(
                "DELETE devices_sensor WHERE id=?"
        );
        statement_m.setLong(1, ds.getId());
        statement_m.executeUpdate();
        statement_ds.setLong(1, ds.getId());
        statement_ds.executeUpdate();
        identityMap.remove(ds.getId());
    }

    private Long getID(DeviceSensor ds) throws SQLException, IllegalArgumentException {
        PreparedStatement statement = con.prepareStatement(
                "SELECT id FROM devices_sensor" +
                        "WHERE room_id=? AND name=?"
        );
        statement.setLong(1, ds.getRoom_id());
        statement.setString(2, ds.getName());
        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                return rs.getLong(1);
            }
        }
        throw new IllegalArgumentException(String.valueOf(ds.getName() + " in room " + ds.getRoom_id()));
    }

    public boolean isExistsSensor(Long id) throws SQLException {
        PreparedStatement statement = con.prepareStatement(
                "SELECT id FROM devices_sensor WHERE id=?"
        );
        statement.setLong(1, id);
        try (ResultSet rs = statement.executeQuery()) {
            return rs.next();
        }
    }


}
