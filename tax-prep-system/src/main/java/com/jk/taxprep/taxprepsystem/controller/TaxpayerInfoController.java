package com.jk.taxprep.taxprepsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<TaxpayerInfo> getTaxpayerInfoById(@PathVariable Long taxpayerId) {
        TaxpayerInfo taxpayerInfo = taxpayerInfoService.getTaxpayerInfoById(taxpayerId);
        return ResponseEntity.ok(taxpayerInfo);
    }

    @PostMapping("/add")
    public ResponseEntity<TaxpayerInfo> addTaxpayerInfo(@RequestBody TaxpayerInfo taxpayerInfo) {
        TaxpayerInfo newTaxpayerInfo = taxpayerInfoService.saveTaxpayerInfo(taxpayerInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTaxpayerInfo);
    }

    @PutMapping("/update")
    public ResponseEntity<TaxpayerInfo> updateTaxpayerInfo(@RequestBody TaxpayerInfo taxpayerInfo) {
        TaxpayerInfo updatedTaxpayerInfo = taxpayerInfoService.updateTaxpayerInfo(taxpayerInfo);
        return ResponseEntity.ok(updatedTaxpayerInfo);
    }

}
