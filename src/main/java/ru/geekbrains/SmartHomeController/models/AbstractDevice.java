package ru.geekbrains.SmartHomeController.models;


import ru.geekbrains.SmartHomeController.intrfaces.Device;

public class AbstractDevice {
    Device implementor;

    public AbstractDevice(Device implementor){
        this.implementor=implementor;
    }
}
