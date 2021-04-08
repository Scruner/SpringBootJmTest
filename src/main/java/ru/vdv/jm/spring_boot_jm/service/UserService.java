package ru.vdv.jm.spring_boot_jm.service;

import ru.vdv.jm.spring_boot_jm.models.Role;
import ru.vdv.jm.spring_boot_jm.models.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    void addUser(User user);

    void deleteById(Long id);

    void updateUser(User user);

    User findById(Long id);

    List<Role> findAllRoles();

    Role getRoleById(Long id);

    User findByEmail(String email);


}

