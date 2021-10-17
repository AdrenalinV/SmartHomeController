package ru.geekbrains.SmartHomeController.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import ru.geekbrains.SmartHomeController.intrfaces.Device;
import ru.geekbrains.SmartHomeController.models.ObservableSubject;

@Data
@NoArgsConstructor
public class DeviceSensor extends ObservableSubject implements Device {

    private Long id;
    private String name;
    private String type;
    private double meaning;
    private Long room_id;

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
