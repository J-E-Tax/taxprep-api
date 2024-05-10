package com.jk.taxprep.taxprepsystem.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.http.MediaType.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jk.taxprep.taxprepsystem.service.UserService;
import com.jk.taxprep.taxprepsystem.model.User;
import com.jk.taxprep.taxprepsystem.config.JwtTokenProvider;


@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetUserById() throws Exception {
        Long userId = (long) 1;
        User mockUser = new User("eric@ericeric@.com", "123321", "USER", null);
        mockUser.setUserId(userId);

        given(userService.getUserById(userId)).willReturn(mockUser);

        mockMvc.perform(get("/users/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("eric@ericeric@.com"));
    }

    @Test
    public void testRegisterUser() throws Exception {
        User user = new User("eric@ericeric@.com", "123321", "USER", null);

        mockMvc.perform(post("/users/register")
        .contentType(APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(user)))
        .andExpect(status().isCreated());

    }

}
