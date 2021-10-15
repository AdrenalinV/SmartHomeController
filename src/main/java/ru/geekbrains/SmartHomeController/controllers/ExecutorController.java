package ru.geekbrains.SmartHomeController.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.SmartHomeController.dto.StatusDTO;
import ru.geekbrains.SmartHomeController.entities.DeviceExecutor;
import ru.geekbrains.SmartHomeController.services.DeviceExecutorService;


import java.util.List;

@RestController
@RequestMapping("/api/v1/executor")
public class ExecutorController {
    @Autowired
    private DeviceExecutorService deviceExecutorService;

    @GetMapping()
    public List<DeviceExecutor> getFreeExecutor() {
        return deviceExecutorService.getFreeExecutor();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void setExecutorStatus(@RequestBody StatusDTO statusDTO) {
        deviceExecutorService.updateExecutorStatus(statusDTO);
    }

    @PutMapping
    public void updateExecutor(@RequestBody DeviceExecutor de) {
        deviceExecutorService.updateExecutor(de);
    }

    @GetMapping("/{id}")
    public DeviceExecutor getExecutorById(@PathVariable Long id) {
        return deviceExecutorService.getDeviceExecutorByID(id);
    }

    @DeleteMapping("/{id}")
    public void toFreeExecutor(@PathVariable Long id) {
        deviceExecutorService.deleteExecutor(id);
    }
}
