package ru.vdv.jm.spring_boot_jm.service;

import ru.vdv.jm.spring_boot_jm.models.Role;
import ru.vdv.jm.spring_boot_jm.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void addUser(User user, String[] role);

    void deleteUser(int id);

    void updateUser(User user, String[] role);

    User getUserById(int id);

    Role getRoleByName(String role);

    User getUserByEmail(String email);

}

