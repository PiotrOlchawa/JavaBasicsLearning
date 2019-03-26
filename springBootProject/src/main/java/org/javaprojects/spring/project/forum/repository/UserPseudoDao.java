package org.javaprojects.spring.project.forum.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.javaprojects.spring.project.forum.domain.User;
import org.javaprojects.spring.project.forum.exception.UserNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class UserPseudoDao {

    public static List<User> users = new ArrayList<>();

    static Integer userCount = 4;
    public static User user1 = new User(1, "Piotr", new Date());
    public static User user2 = new User(2, "Adam", new Date());
    public static User user3 = new User(3, "Waldek", new Date());
    public static User user4 = new User(4, "Rysiek", new Date());


    static {
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(Integer userId) {
        Optional<User> matchedUser = users.stream()
                .filter(l -> l.getId().equals(userId))
                .findFirst();
        return matchedUser.orElseThrow(() -> new UserNotFoundException("User Id " + userId));
    }
}
