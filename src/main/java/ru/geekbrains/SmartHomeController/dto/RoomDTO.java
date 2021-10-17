package ru.geekbrains.SmartHomeController.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.SmartHomeController.entities.DeviceSensor;

import java.util.List;

@Data
@NoArgsConstructor
public class RoomDTO {
    private Long id;
    private String name;
    private List<DeviceSensorDTO> deviceSensorDTOList;
    private List<DeviceExecutorDTO> deviceExecutorDTOlist;
}
