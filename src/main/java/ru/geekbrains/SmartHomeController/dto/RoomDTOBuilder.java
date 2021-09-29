package ru.geekbrains.SmartHomeController.dto;

public class RoomDTOBuilder {
    private final RoomDTO roomDTO;

    public RoomDTOBuilder(){
        roomDTO=new RoomDTO();
    }

    public RoomDTOBuilder name(String name){
        roomDTO.setName(name);
        return this;
    }

    public RoomDTOBuilder addDSensorDTO(DeviceSensorDTO deviceSensorDTO){
        roomDTO.getDeviceSensorsDTO().add(deviceSensorDTO);
        return this;
    }

    public RoomDTOBuilder addDExecutorDTO(DeviceExecutorDTO deviceExecutorDTO){
        roomDTO.getDevicesExecutorDTO().add(deviceExecutorDTO);
        return this;
    }
    public RoomDTO build(){
        return roomDTO;
    }
}
