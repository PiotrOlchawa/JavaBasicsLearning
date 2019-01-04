package org.javaprojects.spring.project.forum.service;

import org.javaprojects.spring.project.forum.domain.User;
import org.javaprojects.spring.project.forum.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userDao.findByName());
        if(!user.isPresent()){
            throw new UsernameNotFoundException("User does not exist");
        }
    }
}
