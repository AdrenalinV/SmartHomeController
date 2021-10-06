package ru.geekbrains.SmartHomeController.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import ru.geekbrains.SmartHomeController.intrfaces.Device;
import ru.geekbrains.SmartHomeController.models.ObservableSubject;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "devices_sensor")
@Data
@NoArgsConstructor
public class DeviceSensor extends ObservableSubject implements Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "meaning")
    private double meaning;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updated_at;
    @ManyToOne
    private Room room;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type=type;
    }

    @Override
    public double getValue() {
        return meaning;
    }

    public void changeMeaning(double meaning){
        this.meaning=meaning;
        notify(meaning);
    }
}
