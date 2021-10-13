package ru.geekbrains.SmartHomeController.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeviceExecutorDTO {
    private String name;
    private String type;
    private boolean status;

}
