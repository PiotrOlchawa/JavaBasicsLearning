package org.javaprojects.spring.project.resthellowword.domain;

public class HellowWordBean {

    String message;

    public HellowWordBean(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "HellowWordBean{" +
                "message='" + message + '\'' +
                '}';
    }
}
