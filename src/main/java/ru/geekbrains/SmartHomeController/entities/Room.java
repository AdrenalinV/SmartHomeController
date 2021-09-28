package ru.geekbrains.SmartHomeController.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "room")
    private Collection<DeviceExecutor> devicesExecutor;
    @OneToMany(mappedBy = "room")
    private Collection<DeviceSensor> deviceSensors;

}
