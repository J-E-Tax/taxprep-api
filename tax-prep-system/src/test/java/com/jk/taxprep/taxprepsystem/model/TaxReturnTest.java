package com.jk.taxprep.taxprepsystem.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class TaxReturnTest {

    @Test
    public void createAndSetTaxReturn() {
        User user = new User();
        user.setUserId((long) 1);

        BigDecimal totalIncome = new BigDecimal("100000");
        BigDecimal totalDeductions = new BigDecimal("20000");
        BigDecimal taxableIncome = new BigDecimal("80000");
        BigDecimal totalFederalTax = new BigDecimal("40000");
        BigDecimal taxRefundOrDue = new BigDecimal("10000");
        String status = "PENDING";

        TaxReturn taxReturn = new TaxReturn(user.getUserId(), totalIncome, totalDeductions, taxableIncome, totalFederalTax, taxRefundOrDue, status);

        assertEquals(user.getUserId(), taxReturn.getUserId());
        assertEquals(totalIncome, taxReturn.getTotalIncome());
        assertEquals(totalDeductions, taxReturn.getTotalDeductions());
        assertEquals(taxableIncome, taxReturn.getTaxableIncome());
        assertEquals(totalFederalTax, taxReturn.getTotalFederalTax());
        assertEquals(taxRefundOrDue, taxReturn.getTaxRefundOrDue());
        assertEquals(status, taxReturn.getStatus());

        taxReturn.setUserId((long) 110);
        taxReturn.setTotalIncome(new BigDecimal("20000000"));
        taxReturn.setTotalDeductions(new BigDecimal("300000"));
        taxReturn.setTaxableIncome(new BigDecimal("19700000"));
        taxReturn.setTotalFederalTax(new BigDecimal("10000000"));
        taxReturn.setTaxRefundOrDue(new BigDecimal("9700000"));
        taxReturn.setStatus("Filed");

        assertEquals((long) 110, taxReturn.getUserId());
        assertEquals(new BigDecimal("20000000"), taxReturn.getTotalIncome());
        assertEquals(new BigDecimal("300000"), taxReturn.getTotalDeductions());
        assertEquals(new BigDecimal("19700000"), taxReturn.getTaxableIncome());
        assertEquals(new BigDecimal("10000000"), taxReturn.getTotalFederalTax());
        assertEquals(new BigDecimal("9700000"), taxReturn.getTaxRefundOrDue());
        assertEquals("Filed", taxReturn.getStatus());


    }
}
