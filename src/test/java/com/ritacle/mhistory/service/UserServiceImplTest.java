package com.ritacle.mhistory.service;

import com.ritacle.mhistory.persistence.model.Response;
import com.ritacle.mhistory.persistence.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    @Test
    public void saveNullUser() {
        Response<User> result = userService.save(null);
        assertEquals(1, result.getErrorMessages().size());
    }

    @Test
    public void saveUserWithNotUniqueMail() {
        User user = new User("User", "User", "jana.krua@gmail.com", "1", "F", new Date(1989 - 14 - 31));
        Response<User> result = userService.save(user);
        assertEquals(1, result.getErrorMessages().size());
    }

    @Test
    public void saveUserWithNotUniqueUsername() {
        User user = new User("Yana", "User", "jana1.krua@gmail.com", "1", "F", new Date(1989 - 14 - 31));
        Response<User> result = userService.save(user);
        assertEquals(1, result.getErrorMessages().size());
    }

    @Test
    public void saveUserWithEmptyFields() {
        User user = new User("User", "", "user@gmail.com", "1", "   ", null, null);
        Response<User> result = userService.save(user);
        assertEquals(4, result.getErrorMessages().size());
    }
}