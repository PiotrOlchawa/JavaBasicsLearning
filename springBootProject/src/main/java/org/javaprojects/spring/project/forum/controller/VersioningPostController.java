package org.javaprojects.spring.project.forum.controller;

import org.javaprojects.spring.project.forum.domain.Post;
import org.javaprojects.spring.project.forum.service.PostService;
import org.javaprojects.spring.project.forum.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VersioningPostController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VersioningPostController.class);

    @Autowired
    UserService userService;
    @Autowired
    PostService postService;

    //Versioning - different URL mapping
    @GetMapping(path = "/v1/users/{id}/posts" )
    public List<Post> getUserPostsVErsioning1(@PathVariable Integer id) {
        LOGGER.info("Invoking v1 URL mapping getUserPosts()");
        List<Post> userPosts = postService.getUserPosts(id);
        return userPosts;
    }
    @GetMapping(path = "/v2/users/{id}/posts" )
    public List<Post> getUserPostsVErsioning2(@PathVariable Integer id) {
        LOGGER.info("Invoking v2 URL mapping getUserPosts()");
        List<Post> userPosts = postService.getUserPosts(id);
        return userPosts;
    }

    //Versioning - request param
    // Invoking GET /users/{id}/posts/param?version=1
    @GetMapping(path = "/users/{id}/posts",params = "version=1")
    public List<Post> getUserPostsVErsioning3(@PathVariable Integer id) {
        LOGGER.info("Invoking v3 request param getUserPosts()");
        List<Post> userPosts = postService.getUserPosts(id);
        return userPosts;
    }
    @GetMapping(path = "/users/{id}/posts",params = "version=2")
    public List<Post> getUserPostsVErsioning4(@PathVariable Integer id) {
        LOGGER.info("Invoking v4 request param getUserPosts()");
        List<Post> userPosts = postService.getUserPosts(id);
        return userPosts;
    }

    //Versioning - header versioning
    // Invoking GET set HTTP header to key X-API-VERSION and value 1
    @GetMapping(path = "/users/{id}/posts/header",headers = "X-API-VERSION=1")
    public List<Post> getUserPostsVErsioning5(@PathVariable Integer id) {
        LOGGER.info("Invoking v5 header getUserPosts()");
        List<Post> userPosts = postService.getUserPosts(id);
        return userPosts;
    }
    @GetMapping(path = "/users/{id}/posts/header",headers = "X-API-VERSION=2")
    public List<Post> getUserPostsVErsioning6(@PathVariable Integer id) {
        LOGGER.info("Invoking v6 header getUserPosts()");
        List<Post> userPosts = postService.getUserPosts(id);
        return userPosts;
    }

    //Versioning - produces (header parameter)
    // Invoking GET set HTTP header to key Accept and value application.vnd.company.app-v1+json
    @GetMapping(path = "/users/{id}/posts/produces",produces = "application/vnd.company.app-v1+json")
    public List<Post> getUserPostsVErsioning7(@PathVariable Integer id) {
        LOGGER.info("Invoking v7 produces getUserPosts()");
        List<Post> userPosts = postService.getUserPosts(id);
        return userPosts;
    }
    @GetMapping(path = "/users/{id}/posts/produces",produces = "application/vnd.company.app-v2+json")
    public List<Post> getUserPostsVErsioning8(@PathVariable Integer id) {
        LOGGER.info("Invoking v8 produces getUserPosts()");
        List<Post> userPosts = postService.getUserPosts(id);
        return userPosts;
    }


}
