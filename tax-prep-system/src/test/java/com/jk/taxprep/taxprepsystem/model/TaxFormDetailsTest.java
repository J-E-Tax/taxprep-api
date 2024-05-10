package com.jk.taxprep.taxprepsystem.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class TaxFormDetailsTest {

    @Test
    public void testSettersAndGetters() {
        TaxFormDetails details = new TaxFormDetails();
        details.setSsn("111-11-1111");
        details.setEmployerIdentificationNumber("000111222");
        details.setControlNumber("09876");
        details.setEmployeeName("Sony");
        details.setWages(new BigDecimal("100000"));
        details.setFederalIncomeTaxWithheld(new BigDecimal("10000"));
        details.setSocialSecurityWages(new BigDecimal("4000"));
        details.setSocialSecurityTaxWithheld(new BigDecimal("5000"));
        details.setMedicareWagesAndTips(new BigDecimal("50000"));
        details.setMedicareTaxWithheld(new BigDecimal("1234"));
        details.setSocialSecurityTips(new BigDecimal("4321"));
        details.setAllocatedTips(new BigDecimal("444"));
        details.setNothingField("xxx");
        details.setDependentCareBenefits(new BigDecimal("1123"));
        details.setNonqualifiedPlans(new BigDecimal("998"));
        details.setTaxA(new BigDecimal("11"));
        details.setTaxB(new BigDecimal("222"));
        details.setTaxC(new BigDecimal("33"));
        details.setTaxD(new BigDecimal("44"));

        assertEquals("111-11-1111", details.getSsn());
        assertEquals("000111222", details.getEmployerIdentificationNumber());
        assertEquals("09876", details.getControlNumber());
        assertEquals("Sony", details.getEmployeeName());
        assertEquals(new BigDecimal("100000"), details.getWages());
        assertEquals(new BigDecimal("10000"), details.getFederalIncomeTaxWithheld());
        assertEquals(new BigDecimal("4000"), details.getSocialSecurityWages());
        assertEquals(new BigDecimal("5000"), details.getSocialSecurityTaxWithheld());
        assertEquals(new BigDecimal("50000"), details.getMedicareWagesAndTips());
        assertEquals(new BigDecimal("1234"), details.getMedicareTaxWithheld());
        assertEquals(new BigDecimal("4321"), details.getSocialSecurityTips());
        assertEquals(new BigDecimal("444"), details.getAllocatedTips());
        assertEquals("xxx", details.getNothingField());
        assertEquals(new BigDecimal("1123"), details.getDependentCareBenefits());
        assertEquals(new BigDecimal("998"), details.getNonqualifiedPlans());
        assertEquals(new BigDecimal("11"), details.getTaxA());
        assertEquals(new BigDecimal("222"), details.getTaxB());
        assertEquals(new BigDecimal("33"), details.getTaxC());
        assertEquals(new BigDecimal("44"), details.getTaxD());

    }
}
