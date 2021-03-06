package pl.somehost.security.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.somehost.security.spring.domain.User;
import pl.somehost.security.spring.repository.UserDao;
import pl.somehost.security.spring.security.SecurityUser;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LOGGER.info(" User Name " + username);
        Optional<User> user = Optional.ofNullable(userDao.findByUsername(username));

        if(!user.isPresent()){
            LOGGER.info("--------------------------No USER--------------------- ");
        }
        if(user.isPresent()){
            LOGGER.info(" -------------getting user----------------- " + user.get().toString());
        }
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User does not exist");
        }

        return new SecurityUser(user.get());
    }
}
