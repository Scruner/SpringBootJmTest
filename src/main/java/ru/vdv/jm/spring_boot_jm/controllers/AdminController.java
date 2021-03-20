package ru.vdv.jm.spring_boot_jm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String getUsers(Model model) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("users", userService.getAllUsers());
        return "user_list";
    }

    @PostMapping(value = "/add")
    public String addUser(
            @ModelAttribute("user")
                    User user,
            @RequestParam
                    String[] role) {

        userService.addUser(user, role);
        return "redirect:/admin";
    }

    @PostMapping(value = "/edit/{id}")
    public String updateUser(
            @PathVariable
                    int id,
            @ModelAttribute("user")
                    User user,
            @RequestParam
                    String[] role) {

        userService.updateUser(user, role);
        return "redirect:/admin";
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteUserById(
            @PathVariable
                    int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String getUser(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        return "user";
    }
}

