package ru.vdv.jm.spring_boot_jm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user")
    public String getUser() {
        return "user";
    }
}

