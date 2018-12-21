package org.javaprojects.spring.project.forum.service;

import org.javaprojects.spring.project.forum.domain.Post;
import org.javaprojects.spring.project.forum.domain.User;
import org.javaprojects.spring.project.forum.repository.PostDao;
import org.javaprojects.spring.project.forum.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PostDao postDao;

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User getUser(Integer userId) {
        return userDao.findOne(userId);
    }

    public User saveUser(User user) {
        return userDao.save(user);
    }


}
