package ru.geekbrains.SmartHomeController.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
public class RoomDTO {

    private String name;
    private Collection<DeviceExecutorDTO> devicesExecutorDTO;
    private Collection<DeviceSensorDTO> deviceSensorsDTO;


}
