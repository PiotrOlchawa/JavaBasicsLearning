package org.javaprojects.spring.project.forum.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "post")
public class Post {

    private Integer id;
    private String postTitle;
    private String description;
    private User user;

    public Post() {
    }

    public Post(Integer id, String postTitle, String description, User user) {
        this.id = id;
        this.postTitle = postTitle;
        this.description = description;
        this.user = user;
    }

    @XmlElement
    public Integer getId() {
        return id;
    }

    @XmlElement
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement
    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", postTitle='" + postTitle + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}
