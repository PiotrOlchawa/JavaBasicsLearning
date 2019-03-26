package pl.somehost.security.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import pl.somehost.security.spring.domain.User;
import pl.somehost.security.spring.repository.UserDao;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    UserDao userDao;

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public List<User> retriveAllUsers() {
        return userDao.findAll();
    }

}
