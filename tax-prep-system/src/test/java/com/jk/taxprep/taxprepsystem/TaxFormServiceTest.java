package com.jk.taxprep.taxprepsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jk.taxprep.taxprepsystem.model.TaxForm;
import com.jk.taxprep.taxprepsystem.repository.TaxFormRepository;
import com.jk.taxprep.taxprepsystem.service.TaxFormService;

public class TaxFormServiceTest {
    @Mock
    private TaxFormRepository taxFormRepository;

    @InjectMocks
    private TaxFormService taxFormService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllTaxForms() {
        // Prepare mock data
        List<TaxForm> mockTaxForms = new ArrayList<>();
        mockTaxForms.add(new TaxForm(1L, 123, "Pending", ZonedDateTime.now(), "Income", "{}"));
        mockTaxForms.add(new TaxForm(2L, 123, "Approved", ZonedDateTime.now(), "Property", "{}"));

        // Mock the repository method to return mockTaxForms
        when(taxFormRepository.findAll()).thenReturn(mockTaxForms);

        // Call the service method
        List<TaxForm> result = taxFormService.findAllTaxForms();

        // Verify the result
        assertEquals(2, result.size());
        assertEquals("Pending", result.get(0).getStatus());
        assertEquals("Property", result.get(1).getFormType());
    }

    @Test
    public void testFindTaxFormByUserId() {
        int userId = 123;
        List<TaxForm> mockUserTaxes = new ArrayList<>();
        mockUserTaxes.add(new TaxForm(1L, userId, "Pending", ZonedDateTime.now(), "Income", "{}"));

        // Mock the repository method to return mockUserTaxes
        when(taxFormRepository.findByUser(userId)).thenReturn(mockUserTaxes);

        // Call the service method
        List<TaxForm> result = taxFormService.findTaxFormByUserId(userId);

        // Verify the result
        assertEquals(1, result.size());
        assertEquals("Pending", result.get(0).getStatus());
    }

    @Test
    public void testFindTaxFormByFormId() {
        long formId = 1L;
        TaxForm mockTaxForm = new TaxForm(formId, 123, "Pending", ZonedDateTime.now(), "Income", "{}");

        // Mock the repository method to return mockTaxForm
        when(taxFormRepository.findByTaxFormId((int)formId)).thenReturn(mockTaxForm);

        // Call the service method
        TaxForm result = taxFormService.findTaxFormByFormId((int)formId);

        // Verify the result
        assertNotNull(result);
        assertEquals("Pending", result.getStatus());
        assertEquals("Income", result.getFormType());
    }

    @Test
    public void testSaveTaxForm() {
        TaxForm taxForm = new TaxForm(1L, 123, "Pending", ZonedDateTime.now(), "Income", "{}");

        // Mock the repository method to return the same taxForm
        when(taxFormRepository.save(any(TaxForm.class))).thenReturn(taxForm);

        // Call the service method
        TaxForm result = taxFormService.saveTaxForm(taxForm);

        // Verify the result
        assertNotNull(result);
        assertEquals(1L, result.getTaxFormId());
        assertEquals("Pending", result.getStatus());
    }

    @Test
    public void testDeleteTaxForm() {
        TaxForm taxForm = new TaxForm(1L, 123, "Pending", ZonedDateTime.now(), "Income", "{}");

        // Call the service method
        taxFormService.deleteTaxForm(taxForm);

        // Verify that taxFormRepository.delete() was called with the correct taxForm
        verify(taxFormRepository, times(1)).delete(taxForm);
    }
}
