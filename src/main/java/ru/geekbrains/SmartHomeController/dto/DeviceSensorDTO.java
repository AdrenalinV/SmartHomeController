package ru.geekbrains.SmartHomeController.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class DeviceSensorDTO {
    private String name;
    private String type;
    private float meaning;

}
