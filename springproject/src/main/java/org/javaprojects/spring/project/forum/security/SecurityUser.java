package org.javaprojects.spring.project.forum.security;
import org.javaprojects.spring.project.forum.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser extends User implements UserDetails {

    private static final long serialVersionUID = -4381938875186527688L;
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUser.class);

    public SecurityUser() {
    }

    public SecurityUser(User user) {
        super();
        this.setAuthorities(user.getAuthorities());
        this.setId(user.getId());
        this.setPassword(user.getPassword());
        this.setUsername(user.getUsername());
        this.setBirthDate(user.getBirthDate());
        LOGGER.info("passed username : " + user.getUsername() + " password : " +user.getPassword());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
