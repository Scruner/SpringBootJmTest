package ru.vdv.jm.spring_boot_jm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vdv.jm.spring_boot_jm.dao.UserDao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDao userDao;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Autowired
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDao.getUserByEmail(email);
    }

    public static boolean validate(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if (matcher.find()) {
            return true;
        }
        return false;
    }
}
//  "^((\\w|[-+])+(\\.[\\w-]+)*@[\\w-]+(\\.[\\d\\p{Alpha)}+)*(\\.\\p{Alpha}{2,})*)*)"