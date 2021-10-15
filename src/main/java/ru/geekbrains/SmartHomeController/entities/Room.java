package ru.geekbrains.SmartHomeController.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.SmartHomeController.entities.DeviceSensor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
public class Room {
    private Long id;
    private String name;
    private Collection<DeviceSensor> deviceSensors;
    private Collection<DeviceExecutor> deviceExecutors;


//    public Room() {
//        deviceSensors = new ArrayList<>();
//        deviceExecutors = new ArrayList<>();
//    }
}
