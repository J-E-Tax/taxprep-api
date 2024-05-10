package com.jk.taxprep.taxprepsystem.controller;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.security.Principal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jk.taxprep.taxprepsystem.config.JwtTokenProvider;
import com.jk.taxprep.taxprepsystem.model.TaxpayerInfo;
import com.jk.taxprep.taxprepsystem.model.User;
import com.jk.taxprep.taxprepsystem.service.TaxpayerInfoService;
import com.jk.taxprep.taxprepsystem.service.UserService;

@WebMvcTest(TaxpayerInfoController.class)
public class TaxpayerInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaxpayerInfoService taxpayerInfoService;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testGetTaxpayerInfoById() throws Exception {
        Long userId = (long) 1;
        TaxpayerInfo mockTaxpayerInfo = new TaxpayerInfo();
        mockTaxpayerInfo.setTaxpayerInfoId(userId);

        when(taxpayerInfoService.getTaxpayerInfoByUserId(userId)).thenReturn(mockTaxpayerInfo);

        mockMvc.perform(get("/taxpayer-info/{userId}", userId))
               .andExpect(status().isOk())
               .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    public void testAddTaxpayerInfo() throws Exception {
        TaxpayerInfo taxpayerInfo = new TaxpayerInfo();
        User user = new User();
        user.setEmail("eric@ericeric.com");

        when(userService.findByEmail("eric@ericeric.com")).thenReturn(user);
        when(taxpayerInfoService.saveTaxpayerInfo(any(TaxpayerInfo.class))).thenReturn(taxpayerInfo);

        Principal mockPrincipal = () -> "eric@ericeric.com";

        mockMvc.perform(post("/taxpayer-info/add")
                .principal(mockPrincipal)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taxpayerInfo)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

}
