package ru.geekbrains.SmartHomeController.repositories;

import org.springframework.stereotype.Repository;
import ru.geekbrains.SmartHomeController.entities.DeviceExecutor;
import ru.geekbrains.SmartHomeController.services.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DeviceExecutorMapper {
    private final Connection con;
    private Map<Long,DeviceExecutor> identityMap= new HashMap<>();


    public DeviceExecutorMapper() throws SQLException {
        this.con= DataSource.getConnection();
    }

    public DeviceExecutor findById(Long id) throws SQLException, IllegalArgumentException {
        DeviceExecutor de = identityMap.get(id);
        if(de==null){
            PreparedStatement statement = con.prepareStatement(
                    "SELECT id, name, type, status, minValue, maxValue, room_id FROM devices_executor WHERE id=?"
            );
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    de = new DeviceExecutor();
                    de.setId(rs.getLong(1));
                    de.setName(rs.getString(2));
                    de.setType(rs.getString(3));
                    de.setStatus(rs.getInt(4));
                    de.setMinValue(rs.getDouble(5));
                    de.setMaxValue(rs.getDouble(6));
                    de.setRoom_id(rs.getLong(7));
                    identityMap.put(de.getId(),de);
                    return de;
                }
            }
            throw new IllegalArgumentException(String.valueOf(id));
        }else return de;
    }

    public List<DeviceExecutor> getExecutorsByRoom_ID(Long id) throws SQLException {
        List<DeviceExecutor> executors = new ArrayList();
        PreparedStatement statement=con.prepareStatement(
                "SELECT id,name,type,status,room_id FROM devices_executor WHERE room_id=?"
        );
        statement.setLong(1,id);
        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                DeviceExecutor de = new DeviceExecutor();
                de.setId(rs.getLong(1));
                de.setName(rs.getString(2));
                de.setType(rs.getString(3));
                de.setStatus(rs.getInt(4));
                de.setRoom_id(rs.getLong(5));
                executors.add(de);
            }
        }
        return executors;
    }

    public Long insert(DeviceExecutor de) throws SQLException, IllegalArgumentException {
        PreparedStatement statement = con.prepareStatement(
                "INSERT INTO devices_executor (id, name, type, status, minValue, maxValue, room_id) VALUES (?, ?, ?, ?, ?, ?, ?)"
        );
        statement.setLong(1, de.getId());
        statement.setString(2, de.getName());
        statement.setString(3, de.getType());
        statement.setInt(4, de.getStatus());
        statement.setDouble(5, de.getMinValue());
        statement.setDouble(6, de.getMaxValue());
        statement.setLong(7, de.getRoom_id());
        statement.executeUpdate();
        return de.getId();
    }

    public void update(DeviceExecutor de) throws SQLException {
        PreparedStatement statement = con.prepareStatement(
                "UPDATE devices_executor SET status=?,minValue=?,maxValue=? WHERE id=?"
        );
        Long id = (de.getId() != null) ? de.getId() : getID(de);
        statement.setInt(1, de.getStatus());
        statement.setDouble(2, de.getMinValue());
        statement.setDouble(3, de.getMaxValue());
        statement.setLong(4, id);
        statement.executeUpdate();
        identityMap.put(de.getId(),de);
    }

    public void delete(DeviceExecutor de) throws SQLException {
        PreparedStatement statement = con.prepareStatement(
                "DELETE devices_executor WHERE id=?"
        );
        statement.setLong(1,de.getId());
        statement.executeUpdate();
        identityMap.remove(de.getId());
    }

    private Long getID(DeviceExecutor de) throws SQLException, IllegalArgumentException {
        PreparedStatement statement = con.prepareStatement(
                "SELECT id FROM devices_executor" +
                        "WHERE room_id=? AND name=?"
        );
        statement.setLong(1, de.getRoom_id());
        statement.setString(2, de.getName());
        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                return rs.getLong(1);
            }
        }
        throw new IllegalArgumentException(String.valueOf(de.getName() + " in room " + de.getRoom_id()));
    }

    public boolean isExistsExecutor(Long id) throws SQLException {
        PreparedStatement statement = con.prepareStatement(
                "SELECT id FROM devices_executor WHERE id=?"
        );
        statement.setLong(1, id);
        try (ResultSet rs = statement.executeQuery()) {
            return rs.next();
        }
    }
}
