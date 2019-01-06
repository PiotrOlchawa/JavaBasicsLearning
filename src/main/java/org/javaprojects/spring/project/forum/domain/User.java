package org.javaprojects.spring.project.forum.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Swagger
@ApiModel(description = "All details about User")
@XmlRootElement(name = "user")
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID")
    private Integer id;

    @Size(min = 2, message = "Name should have min 2 characters")
    @ApiModelProperty(notes = "Name should have at least two characters") //Swagger
    @Column(name = "USERNAME")
    private String username;

    @JsonIgnore
    @Column(name = "PASSWORD")
    @Size(min = 7, message = "Password should have min 7 characters")
    private String password;

    @Past
    @ApiModelProperty(notes = "Birth date should not be in past") //Swagger
    @Column(name = "BIRTHDATE")
    private Date birthDate;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = Post.class,
            mappedBy = "user"
    )
    private List<Post> postList;

    @JsonIgnore
    @OneToMany(
            targetEntity = Authorities.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "user"
    )
    private Set<Authorities> authorities = new HashSet<>();

    public User() {
    }

    public User(Integer id, String username, Date birthDate) {
        this.id = id;
        this.username = username;
        this.birthDate = birthDate;
    }
    public Set<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authorities> authoritiesSet) {
        this.authorities = authoritiesSet;
    }

    @XmlElement
    public Integer getId() {
        return id;
    }

    @XmlElement
    @JsonIgnore
    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlElement
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthDate=" + birthDate +
                ", postList=" + postList +
                ", authorities=" + authorities +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        return birthDate != null ? birthDate.equals(user.birthDate) : user.birthDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        return result;
    }
}
