package ru.geekbrains.SmartHomeController.models;

import ru.geekbrains.SmartHomeController.intrfaces.Device;

public class BaseDevice extends AbstractDevice{
    public BaseDevice(Device implementor) {
        super(implementor);
    }

    public double getValue(){
        return implementor.getValue();
    }
}
