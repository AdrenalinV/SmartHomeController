package ru.geekbrains.SmartHomeController.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class DeviceExecutorDTO {
    private String name;
    private String type;
    private boolean status;

}
