package org.javaprojects.spring.project.forum.repository;

import org.javaprojects.spring.project.forum.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Integer> {

    @Override
    List<User> findAll();

    Optional<User> findById(Integer id);

    // Jesli nie ma query to wywala lazy inicialization bo w user fetch jest LAZY przy autthorities i postList
    @Query("select u from User u"
            + " left join fetch u.authorities left join fetch u.postList"
            + " where u.username = :username")
    User findByUsername(@Param("username") String username);

}
