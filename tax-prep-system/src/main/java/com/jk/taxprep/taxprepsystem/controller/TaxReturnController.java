package com.jk.taxprep.taxprepsystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jk.taxprep.taxprepsystem.model.TaxForm;
import com.jk.taxprep.taxprepsystem.model.TaxReturn;
import com.jk.taxprep.taxprepsystem.repository.TaxFormRepository;
import com.jk.taxprep.taxprepsystem.repository.TaxReturnRepository;
import com.jk.taxprep.taxprepsystem.service.TaxReturnService;

@RestController
@RequestMapping("/tax-return")
public class TaxReturnController {
    private TaxReturnService taxReturnService;
    private TaxFormRepository taxFormRepository;
    private TaxReturnRepository taxReturnRepository;

    public TaxReturnController(TaxReturnService taxReturnService, TaxFormRepository taxFormRepository, TaxReturnRepository taxReturnRepository) {
        this.taxReturnService = taxReturnService;
        this.taxFormRepository = taxFormRepository;
        this.taxReturnRepository = taxReturnRepository;
    }

    @GetMapping("forms/user/{userId}")
    public ResponseEntity<List<TaxForm>> getTaxFormsByUserId(@PathVariable long userId) {
        try {
            List<TaxForm> taxForms = taxFormRepository.findByUser(userId);
            return ResponseEntity.ok(taxForms);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<Optional<TaxReturn>> getTaxReturnsByUserId(@PathVariable long userId) {
        try {
            Optional<TaxReturn> taxReturn = taxReturnRepository.findByUserId(userId);

            if (taxReturn.isEmpty()) {
                return ResponseEntity.noContent().build(); // This return 204 if no content is found
            }

            return ResponseEntity.ok(taxReturn);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/calculateTaxReturn/{userId}")
    public ResponseEntity<?> calculateAndSaveTaxReturnsForUser(@PathVariable long userId) {
        try {
            TaxReturn taxReturn = taxReturnService.calculateTaxReturnsForUser(userId);

            return ResponseEntity.ok(taxReturn);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
