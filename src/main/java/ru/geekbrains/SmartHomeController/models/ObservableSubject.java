package ru.geekbrains.SmartHomeController.models;

import ru.geekbrains.SmartHomeController.intrfaces.Observer;


import java.util.ArrayList;
import java.util.List;

public abstract class ObservableSubject {
    private List<Observer> observers = new ArrayList<>();
// подключить наблюдателя
    public void attach(Observer observer) {
        observers.add(observer);
    }
// отключить наблюдателя
    public void detach(Observer observer) {
        observers.remove(observer);
    }
// оповестить подписантов об изменениях
    protected void notify(Object arg) {
        for (Observer observer : observers) {
            observer.update(this, arg);
        }
    }
}
