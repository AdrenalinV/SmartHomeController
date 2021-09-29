package ru.geekbrains.SmartHomeController.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class RoomDTO {

    private String name;
    private Collection<DeviceExecutorDTO> devicesExecutorDTO;
    private Collection<DeviceSensorDTO> deviceSensorsDTO;

    public RoomDTO(){
        deviceSensorsDTO = new ArrayList<>();
        devicesExecutorDTO = new ArrayList<>();
    }
}
