package com.jk.taxprep.taxprepsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jk.taxprep.taxprepsystem.model.TaxpayerInfo;
import com.jk.taxprep.taxprepsystem.service.TaxpayerInfoService;

@RestController
@RequestMapping("/taxpayer-info")
public class TaxpayerInfoController {
    private final TaxpayerInfoService taxpayerInfoService;

    public TaxpayerInfoController(TaxpayerInfoService taxpayerInfoService) {
        this.taxpayerInfoService = taxpayerInfoService;
    }

    @GetMapping("/{taxpayerId}")
    public TaxpayerInfo getTaxpayerInfoById(@PathVariable Long taxpayerId) {
        return taxpayerInfoService.getTaxpayerInfoById(taxpayerId);
    }

    @PostMapping("/add")
    public TaxpayerInfo addTaxpayerInfo(@RequestBody TaxpayerInfo taxpayerInfo) {
        return taxpayerInfoService.saveTaxpayerInfo(taxpayerInfo);
    }
}
