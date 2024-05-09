package com.jk.taxprep.taxprepsystem.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.cglib.core.Local;

public class UserTest {

    @Test
    public void testCreateUser() {
        LocalDateTime now = LocalDateTime.now();

        User user = new User("eric@ericeric.com", "ericeric", "USER", now);

        assertEquals("eric@ericeric.com", user.getEmail());
        assertEquals("ericeric", user.getPassword());
        assertEquals("USER", user.getRole());
        assertEquals(now, user.getCreatedAt());
    }

    @Test
    public void testGettersAndSetters() {
        User user = new User();
        LocalDateTime now = LocalDateTime.now();

        user.setUserId((long) 1);
        user.setEmail("eric@ericeric.com");
        user.setPassword("ericeric");
        user.setRole("USER");
        user.setCreatedAt(LocalDateTime.of(2024, 1, 1, 1, 1));
        user.setLastLogin(now);

        assertEquals(1, user.getUserId());
        assertEquals("eric@ericeric.com", user.getEmail());
        assertEquals("ericeric", user.getPassword());
        assertEquals("USER", user.getRole());
        assertEquals(LocalDateTime.of(2024, 1, 1, 1, 1), user.getCreatedAt());
        assertEquals(now, user.getLastLogin());

    }

}
