package ru.geekbrains.SmartHomeController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.geekbrains.SmartHomeController.entities.Room;
import ru.geekbrains.SmartHomeController.services.Registry;
import ru.geekbrains.SmartHomeController.services.RoomService;

import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class SmartHomeControllerApplication {


    public static void main(String[] args) {

        SpringApplication.run(SmartHomeControllerApplication.class, args);


    }

}
