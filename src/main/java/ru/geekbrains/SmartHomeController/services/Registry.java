package ru.geekbrains.SmartHomeController.services;

import ru.geekbrains.SmartHomeController.repositories.DeviceExecutorMapper;
import ru.geekbrains.SmartHomeController.repositories.DeviceSensorMapper;
import ru.geekbrains.SmartHomeController.repositories.RoomMapper;

import java.sql.SQLException;

public class Registry {
    private static Registry instance = new Registry();

    public Registry() {
    }

    public static Registry getInstance() {
        return instance;
    }

    protected DeviceSensorMapper deviceSensorMapper;

    {
        try {
            deviceSensorMapper = new DeviceSensorMapper();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DeviceSensorMapper getDeviceSensorMapper() {
        return deviceSensorMapper;
    }

    protected RoomMapper roomMapper;

    {
        try {
            roomMapper = new RoomMapper();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public RoomMapper getRoomMapper() {
        return roomMapper;
    }

    protected DeviceExecutorMapper deviceExecutorMapper;

    {
        try {
            deviceExecutorMapper = new DeviceExecutorMapper();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DeviceExecutorMapper getDeviceExecutorMapper() {
        return deviceExecutorMapper;
    }


}
