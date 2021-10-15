package ru.geekbrains.SmartHomeController.controllers;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.SmartHomeController.services.RoomService;

@Controller
public class SmartHomeController {
    @Autowired
    private RoomService roomService;

    @RequestMapping()
    public String getAllRooms(Model model){
        model.addAttribute("rooms",roomService.getRoomAll());
        return "index";
    }

    @RequestMapping(value = "/rooms")
    public String getRoomByID(@RequestParam()Long id, Model model){
        model.addAttribute("room", roomService.findRoomByID(id));
        return "rooms";
    }
}
