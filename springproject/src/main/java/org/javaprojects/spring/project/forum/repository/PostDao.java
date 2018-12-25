package org.javaprojects.spring.project.forum.repository;

import org.javaprojects.spring.project.forum.domain.Post;
import org.javaprojects.spring.project.forum.domain.User;
import org.javaprojects.spring.project.forum.exception.PostNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PostDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostDao.class);
    public static List<Post> postsList = new ArrayList<>();
    static Integer postCount = 6;

    static {
        postsList.add(new Post(1, "title 1", "description 1", UserDao.user1));
        postsList.add(new Post(2, "title 11", "description 11", UserDao.user1));
        postsList.add(new Post(3, "title 11", "description 11", UserDao.user1));
        postsList.add(new Post(4, "title 2", "description 2", UserDao.user1));
        postsList.add(new Post(5, "title 22", "description 22", UserDao.user2));
        postsList.add(new Post(6, "title 22", "description 22", UserDao.user2));
        LOGGER.info("Posts Created");
    }

    public List<Post> findByUser(Integer userId) {
        LOGGER.info("findByUser(Integer userId)" + userId);
        List<Post> postListByUser = postsList.stream().filter(l -> l.getUser().getId() == userId)
                .collect(Collectors.toList());
        //LOGGER.info(postList.toString());
        if (postListByUser.size() == 0) {
            throw new PostNotFoundException("Post Not Found");
        } else {
            return postListByUser;
        }
    }

    public Post findById(Integer postId){
        Optional<Post> optionalPost = postsList.stream().filter(l->l.getId()==postId).findFirst();
        if(!optionalPost.isPresent()){
            throw new PostNotFoundException("Post not Found");
        }
        return optionalPost.get();
    }

    public Post save(Post post) {
        post.setId(postCount++);
        postsList.add(post);
        return post;
    }

    public Post delete(Integer id) {
        Optional<Post> deletedPost = postsList.stream().filter(l -> l.getId().equals(id)).findAny();
        if (!deletedPost.isPresent()) {
            throw new PostNotFoundException("No Post Found");
        }
        postsList = postsList.stream().filter(l -> l.getId() != id).collect(Collectors.toList());
        return deletedPost.get();
    }
}

