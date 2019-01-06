package org.javaprojects.spring.project.forum.controller;

import org.javaprojects.spring.project.forum.domain.Post;
import org.javaprojects.spring.project.forum.domain.User;
import org.javaprojects.spring.project.forum.service.PostService;
import org.javaprojects.spring.project.forum.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @Autowired
    PostService postService;

    @GetMapping(path = "/jpa/users/{id}")
    public Resource<User> getUser(@PathVariable Integer id) {
        User user = userService.getUser(id);
        Resource<User> resource = new Resource<>(user);
        ControllerLinkBuilder getUsersLink = ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(this.getClass()).getUsers());
        resource.add(getUsersLink.withRel("all-users"));
        return resource;
    }

    @GetMapping(path = "/jpa/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(path = "/jpa/users")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        User createdUser = userService.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/users/{id}")
    public Resource<User> getPseudoUser(@PathVariable Integer id) {
        User user = userService.getPseudoUser(id);
        Resource<User> resource = new Resource<>(user);
        ControllerLinkBuilder getUsersLink = ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(this.getClass()).getUsers());
        resource.add(getUsersLink.withRel("all-users"));
        return resource;
    }

    @GetMapping(path = "/users")
    public List<User> getPseudoUsers() {
        return userService.getAllPseudoUsers();
    }

    @PostMapping(path = "/users")
    public ResponseEntity createPseudoUser(@Valid @RequestBody User user) {
        User createdUser = userService.savePseudoUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
