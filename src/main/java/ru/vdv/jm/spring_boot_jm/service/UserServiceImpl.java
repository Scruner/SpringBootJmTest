package ru.vdv.jm.spring_boot_jm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vdv.jm.spring_boot_jm.dao.UserDao;
import ru.vdv.jm.spring_boot_jm.models.Role;
import ru.vdv.jm.spring_boot_jm.models.User;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void addUser(User user, String[] role) {
        //берём пароль, шифруем его и сохраняем его пользователю
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.stream(role).map(this::getRoleByName).collect(Collectors.toSet()));
        userDao.addUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    public void updateUser(User user, String[] role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.stream(role).map(this::getRoleByName).collect(Collectors.toSet()));
        userDao.updateUser(user);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public Role getRoleByName(String role) {
        return userDao.getRoleByName(role);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

}
