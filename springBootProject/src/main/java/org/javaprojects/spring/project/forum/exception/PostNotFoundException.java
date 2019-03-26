package org.javaprojects.spring.project.forum.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException {

    public static final Logger LOGGER = LoggerFactory.getLogger(PostNotFoundException.class);

    public PostNotFoundException(String message) {
        super(message);
        LOGGER.info("PostNotFoundException("+message+") " +"thrown");
    }
}
