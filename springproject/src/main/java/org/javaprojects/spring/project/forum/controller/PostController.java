package org.javaprojects.spring.project.forum.controller;

import org.javaprojects.spring.project.forum.domain.Post;
import org.javaprojects.spring.project.forum.service.PostService;
import org.javaprojects.spring.project.forum.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.xml.ws.Response;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class PostController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostController.class);

    @Autowired
    UserService userService;
    @Autowired
    PostService postService;

    // How to return as XML in List Map requrires special maven dependency
    @GetMapping(path = "/users/{id}/posts",headers="Accept=application/xml",produces=MediaType.APPLICATION_XML_VALUE)
    public List<Post> getUserPosts(@PathVariable Integer id) {
        LOGGER.info("Invoking getUserPosts()");
        List<Post> userPosts = postService.getUserPosts(id);
        return userPosts;
    }

    @PostMapping(path = "/users/{id}/posts")
    public ResponseEntity<Post> createPostForUser(@RequestBody Post post, @PathVariable Integer id) {
        Post createdPost = postService.createPostForUser(post,id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{post_id}")
                .buildAndExpand(createdPost.getId())
                .toUri();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        responseHeaders.set("MyResponseHeader", "Link to resource");
        LOGGER.info("location " + location.toString());
        return new ResponseEntity<Post>(createdPost,responseHeaders,HttpStatus.CREATED);
    }

    @GetMapping(path = "/users/{id}/posts/{post_id}")
    public Post getPostForUser(@PathVariable Integer id, @PathVariable Integer post_id) {
        return postService.getPostForUser(id,post_id);
    }

    @GetMapping(path = "/users/{id}/posts/{post_id}/description")
    public Map<String, String> getPostDescriptionForUser(@PathVariable Integer id, @PathVariable Integer post_id) {
        return postService.getPostDescriptionForUser(id,post_id);
    }
    @DeleteMapping(path ="/posts/{post_id}")
    public @ResponseBody Post deletePost(@PathVariable Integer post_id){
        Post post = postService.deletePost(post_id);
        return post;
    }

}
