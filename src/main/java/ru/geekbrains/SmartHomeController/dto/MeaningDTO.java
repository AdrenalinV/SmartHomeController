package ru.geekbrains.SmartHomeController.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MeaningDTO {
    Long id;
    String type;
    double meaning;
}
