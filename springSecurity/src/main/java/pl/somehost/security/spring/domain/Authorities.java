package pl.somehost.security.spring.domain;

import javax.persistence.Id;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity(name = "AUTHORITIES")
@Table(name = "AUTHORITIES")
public class Authorities implements GrantedAuthority {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "AUTHORITY")
    private String authority;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Authorities() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}

