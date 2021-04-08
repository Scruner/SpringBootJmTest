package ru.vdv.jm.spring_boot_jm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vdv.jm.spring_boot_jm.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
