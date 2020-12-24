package ru.vdv.jm.spring_boot_jm.service;

import ru.vdv.jm.spring_boot_jm.models.Role;
import ru.vdv.jm.spring_boot_jm.models.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public void addUser(User user);

    public void deleteUser(int id);

    public void updateUser(User user);

    public User getUserById(int id);

    public Role getRoleByName(String role);

}
