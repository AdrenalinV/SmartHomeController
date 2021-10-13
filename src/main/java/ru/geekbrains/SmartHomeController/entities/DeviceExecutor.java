package ru.geekbrains.SmartHomeController.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.SmartHomeController.intrfaces.Device;
import ru.geekbrains.SmartHomeController.intrfaces.Observer;
import ru.geekbrains.SmartHomeController.models.ObservableSubject;


@Data
@NoArgsConstructor
public class DeviceExecutor implements Device, Observer {
    private Long id;
    private String name;
    private String type;
    private int status;
    private double minValue;
    private double maxValue;
    private Long room_id;

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
        return status;
    }

    @Override
    public void update(ObservableSubject subject, Object arg) {
        double current = Double.parseDouble(arg.toString());
        if (current <= minValue) {
            status = 1;
        } else if (current >= maxValue) {
            status = 0;
        }
    }
}
