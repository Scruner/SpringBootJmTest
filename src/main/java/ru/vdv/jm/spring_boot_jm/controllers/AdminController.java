package ru.vdv.jm.spring_boot_jm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vdv.jm.spring_boot_jm.models.User;
import ru.vdv.jm.spring_boot_jm.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping
    public String getUsers(@ModelAttribute("user")
                                   User user, Model model, HttpTrace.Principal principal) {
        model.addAttribute("allUsers", userService.findAllUsers());
        model.addAttribute("allRoles", userService.findAllRoles());
        model.addAttribute("user", userService.findByEmail(principal.getName()));
        return "user_list";
    }
}

