package com.jk.taxprep.taxprepsystem.service;

import org.springframework.stereotype.Service;

import com.jk.taxprep.taxprepsystem.model.TaxpayerInfo;
import com.jk.taxprep.taxprepsystem.repository.TaxpayerInfoRepository;

@Service
public class TaxpayerInfoService {
    private final TaxpayerInfoRepository taxpayerInfoRepository;

    public TaxpayerInfoService(TaxpayerInfoRepository taxpayerInfoRepository) {
        this.taxpayerInfoRepository = taxpayerInfoRepository;
    }

    public TaxpayerInfo saveTaxpayerInfo(TaxpayerInfo taxpayerInfo) {
        return taxpayerInfoRepository.save(taxpayerInfo);
    }

    public TaxpayerInfo getTaxpayerInfoById(Long taxpayerId) {
        return taxpayerInfoRepository.findById(taxpayerId).orElse(null);
    }

    public TaxpayerInfo updateTaxpayerInfo(TaxpayerInfo taxpayerInfo) {
        return taxpayerInfoRepository.save(taxpayerInfo);
    }
}
