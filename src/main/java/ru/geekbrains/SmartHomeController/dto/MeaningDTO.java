package ru.geekbrains.SmartHomeController.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MeaningDTO {
    private Long id;
    private String type;
    private double meaning;
}
