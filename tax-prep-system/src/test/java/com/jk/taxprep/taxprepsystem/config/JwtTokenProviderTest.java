package com.jk.taxprep.taxprepsystem.config;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jk.taxprep.taxprepsystem.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


public class JwtTokenProviderTest {
    @Mock
    private UserService userService;

    @Mock
    private Authentication authentication;

    private JwtTokenProvider jwtTokenProvider;

    private final String secret = "taxprepSecretKeytaxprepSecretKeytaxprepSecretKey";
    private final int expiration = 3600000;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtTokenProvider = new JwtTokenProvider(secret, expiration, userService);
    }

    @Test
    void validateToken_ValidToken_ReturnsTrue() {
        UserDetails principal = new User("eric@ericeric.com", "1234567", new ArrayList<>());
        when(authentication.getPrincipal()).thenReturn(principal);
        when(userService.findUserIdByEmail("eric@ericeric.com")).thenReturn((long) 1);

        String token = jwtTokenProvider.generateToken(authentication);
        assertTrue(jwtTokenProvider.validateToken(token), "Token should be valid");
    }
}
