package com.softserve.edu.controller;

import com.softserve.edu.entity.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.edu.service.MarathonService;

import java.util.List;

@Controller
public class MarathonController {

    private MarathonService marathonService;

    @Value("${welcome.message}")
    private String message;

    @Autowired
    public MarathonController(MarathonService marathonService) {
        this.marathonService = marathonService;
    }

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("message", message);
        return "index";
    }

    @RequestMapping(value = {"/students"}, method = RequestMethod.GET)
    public String getStudents(Model model) {
        model.addAttribute("students", marathonService.getStudents());
        return "showList";
    }


}
