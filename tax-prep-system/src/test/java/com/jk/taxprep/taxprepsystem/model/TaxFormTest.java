package com.jk.taxprep.taxprepsystem.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.ZonedDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.jk.taxprep.taxprepsystem.config.JwtTokenProvider;
import com.jk.taxprep.taxprepsystem.service.TaxFormService;


public class TaxFormTest {
    

    @Test
    public void testEqualsAndHashCode() {
        // Create two TaxForm objects with identical properties
        TaxForm taxForm1 = new TaxForm(1L, 123, "Pending", ZonedDateTime.now(), "Income", "{}");
        TaxForm taxForm2 = new TaxForm(1L, 123, "Pending", ZonedDateTime.now(), "Income", "{}");

        // Verify that equals method correctly compares the objects
        assertTrue(taxForm1.equals(taxForm2));
        assertTrue(taxForm2.equals(taxForm1));

        // Verify that hashCode method generates the same hash code for equal objects
        assertEquals(taxForm1.hashCode(), taxForm2.hashCode());

        // Create another TaxForm object with different properties
        TaxForm taxForm3 = new TaxForm(2L, 456, "Approved", ZonedDateTime.now(), "Property", "{}");

        // Verify that equals method correctly identifies different objects
        assertFalse(taxForm1.equals(taxForm3));
        assertFalse(taxForm3.equals(taxForm1));

        // Verify that hashCode method generates different hash code for different objects
        assertNotEquals(taxForm1.hashCode(), taxForm3.hashCode());
    }

    @Test
    public void testToString() {
        // Create a TaxForm object with specific properties
        TaxForm taxForm = new TaxForm(1L, 123, "Pending", ZonedDateTime.now(), "Income", "{}");

        // Verify that toString method returns the expected string representation
        String expectedToString = "TaxForm [taxFormId=1, user=123, status=Pending, submittedAt=" + taxForm.getSubmittedAt()
                + ", formType=Income, formDetails={}]";
        assertEquals(expectedToString, taxForm.toString());
    }
}
