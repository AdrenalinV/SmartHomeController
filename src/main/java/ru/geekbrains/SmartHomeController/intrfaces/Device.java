package ru.geekbrains.SmartHomeController.intrfaces;

public interface Device{
    String getName();
    void setName(String name);
    String getType();
    void setType(String type);
    double getValue();
}
