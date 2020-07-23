package com.ritacle.mhistory.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class PasswordServiceImplTest {

    @Autowired
    PasswordService passwordService;

    @Test
    public void validateNullPasswordsInput() {
        List<String> result = passwordService.validatePasswordsInput(null, null);
        assertEquals(2, result.size());
    }

    @Test
    public void validateEmptyPasswordsInput() {
        List<String> result = passwordService.validatePasswordsInput("", "     ");
        assertEquals(2, result.size());
    }

    @Test
    public void validateCorrectPasswordsInput() {
        List<String> result = passwordService.validatePasswordsInput("21", "123");
        assertEquals(0, result.size());
    }

    @Test
    public void compareSamePasswordAndConfirmationPassword() {
        assertTrue(passwordService.comparePasswordAndConfirmationPassword("1111", "1111"));
    }

    @Test
    public void compareDifferentPasswordAndConfirmationPassword() {
        assertFalse(passwordService.comparePasswordAndConfirmationPassword("1211", "1111"));
    }

    @Test
    public void compareSameRawAndEncodedPassword() {
        assertTrue(passwordService.compareRawAndEncodedPassword("1", "$2a$10$vx65JDXg5cH.40IwIh01eOmShF6xTuZ8ujE48kzWBdnXaRoccl69m"));
    }
}