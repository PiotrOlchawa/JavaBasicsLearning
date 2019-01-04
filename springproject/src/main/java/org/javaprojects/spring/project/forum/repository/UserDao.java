package org.javaprojects.spring.project.forum.repository;

import org.javaprojects.spring.project.forum.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository<User,Integer> {

    @Override
    List<User> findAll();

    Optional<User> findById(Integer id);

    User findByName();
}
