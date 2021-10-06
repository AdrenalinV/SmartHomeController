package ru.geekbrains.SmartHomeController.intrfaces;

import ru.geekbrains.SmartHomeController.models.ObservableSubject;

public interface Observer {
    void update(ObservableSubject subject, Object arg);
}
