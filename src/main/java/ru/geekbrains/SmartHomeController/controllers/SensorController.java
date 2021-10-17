package ru.geekbrains.SmartHomeController.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.SmartHomeController.dto.MeaningDTO;
import ru.geekbrains.SmartHomeController.entities.DeviceSensor;
import ru.geekbrains.SmartHomeController.services.DeviceSensorService;


import java.util.List;

@RestController
@RequestMapping("/api/v1/sensor")
public class SensorController {
    @Autowired
    private DeviceSensorService deviceSensorService;

    @GetMapping()
    public List<DeviceSensor> getFreeSensors() {
        return deviceSensorService.getFreeSensor();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void sensorMeaning(@RequestBody MeaningDTO meaning) {
        deviceSensorService.updateSensorValue(meaning);
    }

    @PutMapping
    public void updateSensor(@RequestBody DeviceSensor ds) {
        deviceSensorService.updateSensor(ds);
    }

    @GetMapping("/{id}")
    public DeviceSensor getSensorById(@PathVariable Long id) {
        return deviceSensorService.getDeviceSensorByID(id);
    }

    @DeleteMapping("/{id}")
    public void toFreeSensor(@PathVariable Long id) {
        deviceSensorService.deleteSensor(id);
    }


}
