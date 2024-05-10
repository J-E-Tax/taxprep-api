package com.jk.taxprep.taxprepsystem.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LoginRequestDTOTest {

    @Test
    public void testSettersAndGetters() {
        String email = "eric@ericeric.com";
        String password = "ericeric";
        LoginRequestDTO loginRequest = new LoginRequestDTO();

        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        assertEquals(email, loginRequest.getEmail());
        assertEquals(password, loginRequest.getPassword());
    }

    @Test
    public void testConstructor() {
        String email = "eric@ericeric.com";
        String password = "ericeric";

        LoginRequestDTO loginRequest = new LoginRequestDTO(email, password);

        assertEquals(email, loginRequest.getEmail());
        assertEquals(password, loginRequest.getPassword());
    }
}
