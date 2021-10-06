package ru.geekbrains.SmartHomeController.entities;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import ru.geekbrains.SmartHomeController.intrfaces.Device;
import ru.geekbrains.SmartHomeController.intrfaces.Observer;
import ru.geekbrains.SmartHomeController.models.ObservableSubject;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "devices_executor")
@Data
@NoArgsConstructor
public class DeviceExecutor implements Device, Observer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "status")
    private boolean status;
    @Column(name = "min")
    private double min;
    @Column(name = "max")
    private double max;
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
        this.name = name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public double getValue() {
        if (status) {
            return 1.0d;
        }
        return 0.0d;
    }

    @Override
    public void update(ObservableSubject subject, Object arg) {
        double current = Double.parseDouble(arg.toString());
        if (current <= min) {
            status = true;
        } else if (current >= max) {
            status = false;
        }
    }
}
