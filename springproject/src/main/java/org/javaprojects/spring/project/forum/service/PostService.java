package org.javaprojects.spring.project.forum.service;

import org.javaprojects.spring.project.forum.domain.Post;
import org.javaprojects.spring.project.forum.domain.User;
import org.javaprojects.spring.project.forum.exception.PostNotFoundException;
import org.javaprojects.spring.project.forum.exception.UserNotFoundException;
import org.javaprojects.spring.project.forum.repository.PostDao;
import org.javaprojects.spring.project.forum.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostDao postDao;
    @Autowired
    UserDao userDao;

    public List<Post> getUserPosts(Integer userId) {
        return postDao.findByUser(userId);
    }

    public Post createPostForUser(Post post, Integer userId) {
        Optional<User> user = UserDao.users.stream().filter(l -> l.getId() == userId).findFirst();
        if (!user.isPresent()) {
            throw new UserNotFoundException("There is no such user");
        }
        post.setUser(user.get());
        return postDao.save(post);
    }

    public Map<String, String> getPostDescriptionForUser(Integer userId, Integer post_id) {
        Optional<User> user = UserDao.users.stream().filter(l -> l.getId() == userId).findFirst();
        if (!user.isPresent()) {
            throw new UserNotFoundException("There is no such user");
        }
        Optional<Post> post = PostDao.postsList.stream()
                .filter(l -> l.getUser().equals(user.get()))
                .filter(l -> l.getId() == post_id).findFirst();
        if (!post.isPresent()) {
            throw new PostNotFoundException("There is no such post");
        }
        return Collections.singletonMap("response", post.get().getDescription());
    }

    public Post getPostForUser(Integer userId, Integer post_id) {
        Optional<User> user = UserDao.users.stream().filter(l -> l.getId() == userId).findFirst();
        if (!user.isPresent()) {
            throw new UserNotFoundException("There is no such user");
        }
        Optional<Post> post = PostDao.postsList.stream()
                .filter(l -> l.getUser().equals(user.get()))
                .filter(l -> l.getId() == post_id).findFirst();
        if (!post.isPresent()) {
            throw new PostNotFoundException("There is no such post");
        }
        return post.get();
    }

    public Post deletePost(Integer id) {
        return postDao.delete(id);
    }


    public Post getPost(Integer post_id) {
        return postDao.findById(post_id);
    }
}
