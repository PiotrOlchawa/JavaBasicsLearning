package org.javaprojects.spring.project.forum.controller;

import org.javaprojects.spring.project.forum.domain.Post;
import org.javaprojects.spring.project.forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostFilteringController {

    @Autowired
    PostService postService;

    @GetMapping(path = "/filteredpost/{user_id}")
    public List<Post> getFilteredPost(@PathVariable Integer user_id) {
        return postService.getUserPosts(user_id);
    }
}
