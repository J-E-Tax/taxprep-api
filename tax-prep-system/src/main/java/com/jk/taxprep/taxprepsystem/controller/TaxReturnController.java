package com.jk.taxprep.taxprepsystem.controller;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jk.taxprep.taxprepsystem.model.TaxForm;
import com.jk.taxprep.taxprepsystem.model.TaxReturn;
import com.jk.taxprep.taxprepsystem.model.TaxFormDetails;
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
    public ResponseEntity<List<TaxReturn>> getTaxReturnsByUserId(@PathVariable long userId) {
        try {
            List<TaxReturn> taxReturns = taxReturnRepository.findByUserId(userId);

            if (taxReturns.isEmpty()) {
                return ResponseEntity.noContent().build(); // This return 204 if no content is found
            }

            return ResponseEntity.ok(taxReturns);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // @PostMapping("/saveTaxReturn/{taxFormId}")
    // public ResponseEntity<?> calculateAndSaveTax(@PathVariable long taxFormId) {
    //     try {
    //         TaxForm taxForm = taxFormRepository.findById(taxFormId)
    //         .orElseThrow(() -> new RuntimeException("Tax form not found"));

    //         TaxFormDetails details = TaxFormDetails.fromJson(taxForm.getFormDetails());
    //         TaxReturn taxReturn = taxReturnService.calculatorTaxReturn(details, taxForm.getUser());
    //         TaxReturn savedTaxReturn = taxReturnRepository.save(taxReturn);

    //         return ResponseEntity.ok(savedTaxReturn);
    //     } catch (Exception e) {
    //         return ResponseEntity.badRequest().body("Error: " + e.getMessage());
    //     }
    // }

    @PostMapping("/calculateTaxReturn/{userId}")
    public ResponseEntity<?> calculateAndSaveTaxReturnsForUser(@PathVariable long userId) {
        try {
            List<TaxReturn> taxReturns = taxReturnService.calculateTaxReturnsForUsers(userId);

            return ResponseEntity.status(HttpStatus.CREATED).body(taxReturns);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
