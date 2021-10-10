package ru.geekbrains.SmartHomeController.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

//@Entity
//@Table(name="meanings")
@Data
@NoArgsConstructor
public class SensorMeaning {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="id")
    private Long id;
//    @Column(name="sensor_id")
    private Long sensor_id;
//    @Column(name="meaning")
    private double meaning;
//    @Column(name = "created_at")
//    @CreationTimestamp
    private LocalDateTime created_at;
}
