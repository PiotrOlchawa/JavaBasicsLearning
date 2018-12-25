package org.javaprojects.spring.project.forum.domain;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "post")
@JsonIgnoreProperties(value = {"user"})
@JsonFilter("PostFilter")
@Entity
public class Post {

    @Id
    @GeneratedValue
    @NotNull
    private Integer id;
    private String postTitle;
    private String description;
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "USER_ID")
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
