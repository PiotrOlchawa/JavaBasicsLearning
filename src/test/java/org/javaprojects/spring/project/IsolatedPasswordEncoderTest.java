package org.javaprojects.spring.project;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class IsolatedPasswordEncoderTest {

    @Test
    public void passwordEncoderTest(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("piotr");
        System.out.println(password);
    }
}
