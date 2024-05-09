package com.jk.taxprep.taxprepsystem;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.jk.taxprep.taxprepsystem.config.JwtTokenProvider;
import com.jk.taxprep.taxprepsystem.controller.TaxFormController;
import com.jk.taxprep.taxprepsystem.model.TaxForm;
import com.jk.taxprep.taxprepsystem.service.TaxFormService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest(TaxFormController.class)
public class TaxFormControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private TaxFormService taxFormService;

    @MockBean
    JwtTokenProvider jwtTokenProvider;

    @MockBean
    UserDetailsService userDetailsService;

    @BeforeEach
    public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                             .alwaysDo(MockMvcResultHandlers.print()) // Print MVC result details for debugging
                             .build();
    }

    @Test
    public void testFindAllTaxForms() throws Exception {
        // Prepare mock data
        List<TaxForm> taxForms = new ArrayList<>();
        taxForms.add(new TaxForm(1L, 123, "Pending", ZonedDateTime.now(ZoneId.of("UTC")), "Income", "{\"income\": 50000}"));
        taxForms.add(new TaxForm(2L, 456, "Approved", ZonedDateTime.now(ZoneId.of("UTC")), "Property", "{\"property_value\": 250000}"));

        // Mock the service method to return the mock data
        when(taxFormService.findAllTaxForms()).thenReturn(taxForms);

        // Perform GET request to /taxform/taxes and validate the response
        mockMvc.perform(get("/taxform/taxes"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$").isArray())
               .andExpect(jsonPath("$[0].status").value("Pending"))
               .andExpect(jsonPath("$[1].status").value("Approved"));
    }

    @Test
    public void testCreateTaxForm() throws Exception {
        // Prepare mock tax form data
        ZonedDateTime submittedAt = ZonedDateTime.now(ZoneId.of("UTC"));
        TaxForm newTaxForm = new TaxForm(1L, 123, "Pending", submittedAt, "Income", "{\"income\": 50000}");

        // Mock the service method to save the tax form
        when(taxFormService.saveTaxForm(any(TaxForm.class))).thenReturn(newTaxForm);

        // Perform POST request to /taxform/create with JSON body and validate the response
        mockMvc.perform(post("/taxform/create")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"taxFormId\": 1, \"user\": 123, \"status\": \"Pending\", \"submittedAt\": \"" + submittedAt + "\", \"formType\": \"Income\", \"formDetails\": \"{\\\"income\\\": 50000}\"}"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.status").value("Pending"))
               .andExpect(jsonPath("$.formType").value("Income"));
    }

    @Test
    public void testUpdateTaxForm() throws Exception {
        // Prepare mock tax form data
        TaxForm updatedTaxForm = new TaxForm(1L, 123, "Updated", ZonedDateTime.now(), "Income", "{\"income\": 60000}");

        // Mock the service method to save the tax form
        when(taxFormService.saveTaxForm(any(TaxForm.class))).thenReturn(updatedTaxForm);

        // Perform PUT request to /taxform/taxes/update with JSON body and validate the response
        mockMvc.perform(put("/taxform/taxes/update")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"taxFormId\": 1, \"user\": 123, \"status\": \"Updated\", \"formType\": \"Income\", \"formDetails\": \"{\\\"income\\\": 60000}\"}"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.status").value("Updated"))
               .andExpect(jsonPath("$.formType").value("Income"));
    }

    @Test
public void testDeleteTaxForm() throws Exception {
    // Prepare mock tax form data
    //TaxForm taxFormToDelete = new TaxForm(1L, 123, "Pending", ZonedDateTime.now(), "Income", "{\"income\": 50000}");

    // Perform DELETE request to /taxform/taxes with the tax form object in the request body
    mockMvc.perform(delete("/taxform/taxes")
           .contentType(MediaType.APPLICATION_JSON)
           .content("{\"taxFormId\": 1, \"user\": 123, \"status\": \"Pending\", \"formType\": \"Income\", \"formDetails\": \"{\\\"income\\\": 50000}\"}"))
           .andExpect(status().isNoContent());

    // Verify that taxFormService.deleteTaxForm() was called with the correct taxFormId
    //verify(taxFormService, times(1)).deleteTaxForm(taxFormToDelete);
}
@Test
    public void testFindTaxFormByUserId() throws Exception {
        int userId = 123;
        List<TaxForm> userTaxes = new ArrayList<>();
        userTaxes.add(new TaxForm(1L, userId, "Pending", null, "Income", "{}"));
        userTaxes.add(new TaxForm(2L, userId, "Approved", null, "Property", "{}"));

        // Mock the service method to return userTaxes when called with userId
        when(taxFormService.findTaxFormByUserId(userId)).thenReturn(userTaxes);

        // Perform GET request to /taxform/taxes/users/{userId} and validate the response
        mockMvc.perform(get("/taxform/taxes/users/{userId}", userId))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$").isArray())
               .andExpect(jsonPath("$[0].status").value("Pending"))
               .andExpect(jsonPath("$[1].status").value("Approved"));
    }

    @Test
    public void testFindTaxFormByFormId() throws Exception {
        int formId = 1;
        TaxForm formTaxes = new TaxForm(formId, 123, "Pending", null, "Income", "{}");

        // Mock the service method to return formTaxes when called with formId
        when(taxFormService.findTaxFormByFormId(formId)).thenReturn(formTaxes);

        // Perform GET request to /taxform/taxes/form/{formId} and validate the response
        mockMvc.perform(get("/taxform/taxes/form/{formId}", formId))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.status").value("Pending"))
               .andExpect(jsonPath("$.formType").value("Income"));
    }


// Similarly, write tests for other controller methods (findTaxFormByFormId, createTaxForm, updateTaxForm, deleteTaxForm)...

}
