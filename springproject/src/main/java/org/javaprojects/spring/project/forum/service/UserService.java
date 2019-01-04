package org.javaprojects.spring.project.forum.service;

import org.javaprojects.spring.project.forum.domain.User;
import org.javaprojects.spring.project.forum.repository.PostPseudoDao;
import org.javaprojects.spring.project.forum.repository.UserDao;
import org.javaprojects.spring.project.forum.repository.UserPseudoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserPseudoDao userPseudoDao;
    @Autowired
    UserDao userDao;
    @Autowired
    UserPseudoDao userPseudoDaoDao;

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
