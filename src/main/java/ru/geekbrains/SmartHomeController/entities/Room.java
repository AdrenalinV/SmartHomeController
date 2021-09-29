package ru.geekbrains.SmartHomeController.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "rooms")
@Data
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

    public Room() {
        devicesExecutor = new ArrayList<>();
        deviceSensors = new ArrayList<>();
    }

}
