package ru.geekbrains.SmartHomeController.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatusDTO {
    private Long id;
    private String type;
    private int status;
}
