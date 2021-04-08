package ru.vdv.jm.spring_boot_jm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vdv.jm.spring_boot_jm.models.User;
import ru.vdv.jm.spring_boot_jm.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminRestController {

    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(
            @RequestBody
                    User user) {
        userService.addUser(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<User> updateUser(
            @RequestBody
                    User user) {
        userService.updateUser(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(
            @PathVariable
                    Long id) {
        userService.deleteById(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable
                    Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }
}

