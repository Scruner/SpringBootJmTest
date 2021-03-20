package ru.vdv.jm.spring_boot_jm.dao;

import ru.vdv.jm.spring_boot_jm.models.Role;
import ru.vdv.jm.spring_boot_jm.models.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    void addUser(User user);

    void deleteUser(int id);

    void updateUser(User user);

    User getUserById(int id);

    User getUserByUsername(String name);

    Role getRoleByName(String role);

    User getUserByEmail(String email);
}
