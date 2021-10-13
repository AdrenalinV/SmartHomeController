package ru.geekbrains.SmartHomeController.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomDTOBuilder {
    private final RoomDTO roomDTO;

    public RoomDTOBuilder(){
        roomDTO=new RoomDTO();
    }

    public RoomDTOBuilder name(String name){
        roomDTO.setName(name);
        return this;
    }

    public RoomDTOBuilder addDSensorDTO(List<DeviceSensorDTO> deviceSensorsDTO){
        roomDTO.setDeviceSensorsDTO(deviceSensorsDTO);
        return this;
    }

    public RoomDTOBuilder addDExecutorDTO(List<DeviceExecutorDTO> deviceExecutorsDTO){
        roomDTO.setDevicesExecutorDTO(deviceExecutorsDTO);
        return this;
    }
    public RoomDTO build(){
        return roomDTO;
    }
}
