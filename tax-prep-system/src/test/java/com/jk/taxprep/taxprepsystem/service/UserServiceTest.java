package com.jk.taxprep.taxprepsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.jk.taxprep.taxprepsystem.model.User;
import com.jk.taxprep.taxprepsystem.repository.UserRepository;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUserById() {
        User user = new User("eric@ericeric.com", "ericeric", "USER", null);

        user.setUserId((long) 1);

        when(userRepository.findById((long)1)).thenReturn(Optional.of(user));

        User userFound = userService.getUserById((long) 1);

        assertNotNull(userFound);
        assertEquals("eric@ericeric.com", userFound.getEmail());
    }

    @Test
    public void testLoadUserByUsername() {
        User user = new User("eric@ericeric.com", "ericeric", "USER", null);

        when(userRepository.findByEmail("eric@ericeric.com")).thenReturn(Optional.of(user));

        org.springframework.security.core.userdetails.UserDetails userDetails = userService.loadUserByUsername("eric@ericeric.com");
        assertNotNull(userDetails);
        assertEquals("eric@ericeric.com", userDetails.getUsername());
    }

    @Test
    public void testFindOrCreateUser() {
        User user = new User("eric@ericeric.com", "ericeric", "USER", null);
        when(userRepository.findByEmail("eric@ericeric.com")).thenReturn(Optional.of(user));

        User foundUser = userService.findOrCreateUser("eric@ericeric.com");

        assertNotNull(foundUser);
        assertEquals("eric@ericeric.com", foundUser.getEmail());
    }

}
