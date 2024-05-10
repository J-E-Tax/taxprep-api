package com.jk.taxprep.taxprepsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.jk.taxprep.taxprepsystem.model.TaxForm;
import com.jk.taxprep.taxprepsystem.model.TaxFormDetails;
import com.jk.taxprep.taxprepsystem.repository.TaxFormRepository;
import com.jk.taxprep.taxprepsystem.model.TaxReturn;
import com.jk.taxprep.taxprepsystem.repository.TaxReturnRepository;

@ExtendWith(MockitoExtension.class)
public class TaxReturnServiceTest {

    @Mock
    TaxFormRepository taxFormRepository;

    @Mock
    TaxReturnRepository taxReturnRepository;

    @InjectMocks
    TaxReturnService taxReturnService;

    @Test
    public void testCalculatorTaxRetur(){
        TaxFormDetails taxFormDetails = new TaxFormDetails(
            "123-45-6789", "987654321", "123456", "John Doe",
            new BigDecimal("111"), new BigDecimal("333"),
            new BigDecimal("12345"), new BigDecimal("54321"),
            new BigDecimal("33333"), new BigDecimal("989"),
            new BigDecimal("1000"), new BigDecimal("989"),
            "lol", new BigDecimal("99"), new BigDecimal("998"),
            new BigDecimal("111"), new BigDecimal("2220"),
            new BigDecimal("111"), new BigDecimal("333"));

            long userId = 1;

            TaxReturn taxReturn = taxReturnService.calculatorTaxReturn(taxFormDetails, userId, null);

            assertEquals(userId, taxReturn.getUserId());
            assertEquals(new BigDecimal("111"), taxReturn.getTotalIncome());
            assertEquals(new BigDecimal("14600"), taxReturn.getTotalDeductions());
            assertEquals(new BigDecimal("-14489"), taxReturn.getTaxableIncome());
            assertEquals(new BigDecimal("0"), taxReturn.getTotalFederalTax());
            assertEquals(new BigDecimal("333"), taxReturn.getTaxRefundOrDue());
    }
}
