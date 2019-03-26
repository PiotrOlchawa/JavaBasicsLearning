package org.javaprojects.spring.project.forum.service;

import org.javaprojects.spring.project.forum.domain.User;
import org.javaprojects.spring.project.forum.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

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
