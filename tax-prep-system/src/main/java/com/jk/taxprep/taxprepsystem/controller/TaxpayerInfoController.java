package com.jk.taxprep.taxprepsystem.controller;

import org.springframework.http.ResponseEntity;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jk.taxprep.taxprepsystem.model.TaxpayerInfo;
import com.jk.taxprep.taxprepsystem.model.User;
import com.jk.taxprep.taxprepsystem.service.TaxpayerInfoService;
import com.jk.taxprep.taxprepsystem.service.UserService;

@RestController
@RequestMapping("/taxpayer-info")
public class TaxpayerInfoController {
    private final TaxpayerInfoService taxpayerInfoService;
    private final UserService userService;

    public TaxpayerInfoController(TaxpayerInfoService taxpayerInfoService, UserService userService) {
        this.taxpayerInfoService = taxpayerInfoService;
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<TaxpayerInfo> getTaxpayerInfoById(@PathVariable Long userId) {
        TaxpayerInfo taxpayerInfo = taxpayerInfoService.getTaxpayerInfoByUserId(userId);
        return ResponseEntity.ok(taxpayerInfo);
    }

    // @PostMapping("/add")
    // public ResponseEntity<TaxpayerInfo> addTaxpayerInfo(@RequestBody TaxpayerInfo taxpayerInfo) {
    //     TaxpayerInfo newTaxpayerInfo = taxpayerInfoService.saveTaxpayerInfo(taxpayerInfo);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(newTaxpayerInfo);
    // }

    @PostMapping("/add")
    public ResponseEntity<TaxpayerInfo> addTaxpayerInfo(@RequestBody TaxpayerInfo taxpayerInfo, Principal principal) { // user current user to add taxpayer info

        String email = principal.getName();
        User user = userService.findByEmail(email);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        taxpayerInfo.setUser(user);

        TaxpayerInfo newTaxpayerInfo = taxpayerInfoService.saveTaxpayerInfo(taxpayerInfo);

        return ResponseEntity.status(HttpStatus.CREATED).body(newTaxpayerInfo);
    }


    @PutMapping("/update")
    public ResponseEntity<TaxpayerInfo> updateTaxpayerInfo(@RequestBody TaxpayerInfo taxpayerInfo) {
        TaxpayerInfo updatedTaxpayerInfo = taxpayerInfoService.updateTaxpayerInfo(taxpayerInfo);
        return ResponseEntity.ok(updatedTaxpayerInfo);
    }

}
