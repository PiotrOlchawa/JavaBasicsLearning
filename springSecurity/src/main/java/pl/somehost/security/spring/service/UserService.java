package pl.somehost.security.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.somehost.security.spring.domain.User;
import pl.somehost.security.spring.repository.UserDao;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User getUser(Integer userId) {
        return Optional.ofNullable(userDao.findById(userId)).orElseThrow(RuntimeException::new).get();
    }

    public User saveUser(User user) {
        return userDao.save(user);
    }

    public List<User> getAllPseudoUsers() {
        return userDao.findAll();
    }

    public User getPseudoUser(Integer userId) {
        return Optional.ofNullable(userDao.findById(userId)).orElseThrow(RuntimeException::new).get();
    }

    public User savePseudoUser(User user) {
        return userDao.save(user);
    }

}
