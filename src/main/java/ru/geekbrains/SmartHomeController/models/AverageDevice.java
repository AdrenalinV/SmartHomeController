package ru.geekbrains.SmartHomeController.models;

import ru.geekbrains.SmartHomeController.intrfaces.Device;

import java.util.LinkedList;
import java.util.Queue;

public class AverageDevice extends AbstractDevice{
    private final Queue<Double> queue = new LinkedList<>();
    private final int n;

    public AverageDevice(Device implementor, int n) {
        super(implementor);
        this.n=n;
    }

    public double getAverageValue(){
        queue.add(implementor.getValue());
        if(queue.size()>n){
            queue.remove();
        }
        return queue.stream().mapToDouble(a->a).average().orElse(0);
    }
}
