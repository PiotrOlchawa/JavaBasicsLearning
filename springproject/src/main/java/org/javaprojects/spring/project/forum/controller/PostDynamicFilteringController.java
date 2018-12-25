package org.javaprojects.spring.project.forum.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.javaprojects.spring.project.forum.domain.Post;
import org.javaprojects.spring.project.forum.domain.Test;
import org.javaprojects.spring.project.forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostDynamicFilteringController {

    @Autowired
    PostService postService;

    @GetMapping(value = "/dynamicfilteredpost/{post_id}")
    public MappingJacksonValue dynamicFilteredPost(@PathVariable Integer post_id) {
        Post post = postService.getPost(post_id);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id");

        FilterProvider filters = new SimpleFilterProvider().addFilter("PostFilter", filter);
        //PostFilter must be added in Post bean as @JsonFilter("PostFilter")

        MappingJacksonValue mapping = new MappingJacksonValue(post);

        mapping.setFilters(filters);
        return mapping;
    }

}
