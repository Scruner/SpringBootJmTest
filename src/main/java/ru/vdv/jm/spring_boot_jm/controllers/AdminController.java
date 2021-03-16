package ru.vdv.jm.spring_boot_jm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.logging.LoggersEndpoint;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vdv.jm.spring_boot_jm.models.Role;
import ru.vdv.jm.spring_boot_jm.models.User;
import ru.vdv.jm.spring_boot_jm.service.UserService;

import java.security.Principal;
import java.util.Set;

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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("users", userService.getAllUsers());
        return "user_list";
    }

    @GetMapping(value = "/add")
    public String showAddUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user_add";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam String[] role) {

        userService.addUser(user, role);
        return "redirect:/admin";
    }

    @GetMapping(value = "/edit/{id}")
    public String showEditUser(Model model, @PathVariable int id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user_edit";
    }

    @PostMapping(value = "/edit/{id}")
    public String updateUser(@PathVariable int id,
                             @ModelAttribute("user") User user,
                             @RequestParam String[] role) {

        userService.updateUser(user, role);
        return "redirect:/admin";
    }

    @GetMapping(value = "/{id}")
    public String getUserById(Model model, @PathVariable int id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user_delete";
    }

    @GetMapping(value = "/delete/{id}")
    public String showDeleteUserById(Model model, @PathVariable int id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user_delete";
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteUserById(@PathVariable int id) {
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

