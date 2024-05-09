package com.jk.taxprep.taxprepsystem.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaxpayerInfoTest {

    @Test
    public void testCreateAndSetTaxpayerInfo() {
        User user = new User();
        user.setUserId((long) 1);

        String address = "{\"street\":\"110 Happy Rd\",\"city\":\"No Where\",\"state\":\"Nevada\",\"zip\":\"89101\"}";

        TaxpayerInfo taxpayerInfo = new TaxpayerInfo(user, "Eric", "Joe", "Chang", "Software Developer", "123-45-6789", LocalDate.of(2000, 1, 1), "111-111-1111", address);

        assertEquals(user, taxpayerInfo.getUser());
        assertEquals("Eric", taxpayerInfo.getFirstName());
        assertEquals("Joe", taxpayerInfo.getMiddleName());
        assertEquals("Chang", taxpayerInfo.getLastName());
        assertEquals("Software Developer", taxpayerInfo.getOccupation());
        assertEquals("123-45-6789", taxpayerInfo.getSsn());
        assertEquals(LocalDate.of(2000, 1, 1), taxpayerInfo.getDob());
        assertEquals("111-111-1111", taxpayerInfo.getPhoneNumber());
        assertEquals(address, taxpayerInfo.getAddress());

        taxpayerInfo.setFirstName("Tracy");
        taxpayerInfo.setMiddleName("M");
        taxpayerInfo.setLastName("McGrady");
        taxpayerInfo.setOccupation("Basketball Player");
        taxpayerInfo.setSsn("987-65-4321");
        taxpayerInfo.setDob(LocalDate.of(1979, 5, 24));
        taxpayerInfo.setPhoneNumber("222-222-2222");
        taxpayerInfo.setAddress("{\"street\":\"111 Yao St\",\"city\":\"Orlando\",\"state\":\"Florida\",\"zip\":\"10010\"}");

        assertEquals("Tracy", taxpayerInfo.getFirstName());
        assertEquals("M", taxpayerInfo.getMiddleName());
        assertEquals("McGrady", taxpayerInfo.getLastName());
        assertEquals("Basketball Player", taxpayerInfo.getOccupation());
        assertEquals("987-65-4321", taxpayerInfo.getSsn());
        assertEquals(LocalDate.of(1979, 5, 24), taxpayerInfo.getDob());
        assertEquals("222-222-2222", taxpayerInfo.getPhoneNumber());
        assertEquals("{\"street\":\"111 Yao St\",\"city\":\"Orlando\",\"state\":\"Florida\",\"zip\":\"10010\"}", taxpayerInfo.getAddress());

    }

}
