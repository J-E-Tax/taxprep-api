package com.jk.taxprep.taxprepsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.jk.taxprep.taxprepsystem.repository.TaxpayerInfoRepository;
import com.jk.taxprep.taxprepsystem.model.TaxpayerInfo;

@ExtendWith(MockitoExtension.class)
public class TaxpayerInfoServiceTest {

    @Mock
    private TaxpayerInfoRepository taxpayerInfoRepository;

    @InjectMocks
    private TaxpayerInfoService taxpayerInfoService;

    @Test
    public void testSaveTaxpayerInfo() {

        TaxpayerInfo taxpayerInfo = new TaxpayerInfo();
        when(taxpayerInfoRepository.save(taxpayerInfo)).thenReturn(taxpayerInfo);

        TaxpayerInfo savedTaxpayerInfo = taxpayerInfoService.saveTaxpayerInfo(taxpayerInfo);

        assertEquals(taxpayerInfo, savedTaxpayerInfo);
        verify(taxpayerInfoRepository).save(taxpayerInfo);

    }

    @Test
    public void testGetTaxpayerInfoById() {

        TaxpayerInfo taxpayerInfo = new TaxpayerInfo();
        taxpayerInfo.setTaxpayerInfoId((long) 1);

        when(taxpayerInfoRepository.findById((long) 1)).thenReturn(java.util.Optional.of(taxpayerInfo));

        TaxpayerInfo taxpayerInfoFound = taxpayerInfoService.getTaxpayerInfoById((long) 1);

        assertEquals((long) 1, taxpayerInfoFound.getTaxpayerInfoId());
        verify(taxpayerInfoRepository).findById((long) 1);

    }

    @Test
    public void testGetTaxpayerInfoByUserId() {

        TaxpayerInfo taxpayerInfo = new TaxpayerInfo();
        taxpayerInfo.setTaxpayerInfoId((long) 1);

        when(taxpayerInfoRepository.findByUser_UserId((long) 1)).thenReturn(taxpayerInfo);

        TaxpayerInfo taxpayerInfoFound = taxpayerInfoService.getTaxpayerInfoByUserId((long) 1);

        assertEquals((long) 1, taxpayerInfoFound.getTaxpayerInfoId());
        verify(taxpayerInfoRepository).findByUser_UserId((long) 1);

    }

}
