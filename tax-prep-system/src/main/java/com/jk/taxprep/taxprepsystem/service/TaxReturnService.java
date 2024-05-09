package com.jk.taxprep.taxprepsystem.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jk.taxprep.taxprepsystem.model.TaxForm;
import com.jk.taxprep.taxprepsystem.model.TaxReturn;
import com.jk.taxprep.taxprepsystem.model.TaxFormDetails;
import com.jk.taxprep.taxprepsystem.repository.TaxFormRepository;
import com.jk.taxprep.taxprepsystem.repository.TaxReturnRepository;

import io.jsonwebtoken.io.IOException;

@Service
public class TaxReturnService {

    private final TaxFormRepository taxFormRepository;
    private final TaxReturnRepository taxReturnRepository;

    public TaxReturnService(TaxFormRepository taxFormRepository, TaxReturnRepository taxReturnRepository) {
        this.taxFormRepository = taxFormRepository;
        this.taxReturnRepository = taxReturnRepository;
    }

    @Transactional
    public TaxReturn calculatorTaxReturn(TaxFormDetails taxFormDetails, long userId, TaxReturn existingTaxReturn) {

        BigDecimal totalIncome = taxFormDetails.getWages();
        BigDecimal totalDeductions = calculateStandardDeduction();
        BigDecimal taxableIncome = totalIncome.subtract(totalDeductions);
        BigDecimal totalFederalTax = calculateFederalTax(taxableIncome);
        BigDecimal taxRefundOrDue = taxFormDetails.getFederalIncomeTaxWithheld().subtract(totalFederalTax);

        if (existingTaxReturn == null) {
            existingTaxReturn = new TaxReturn();
            existingTaxReturn.setStatus("PENDING");
        }

        existingTaxReturn.setUserId(userId);
        existingTaxReturn.setTotalIncome(totalIncome);
        existingTaxReturn.setTotalDeductions(totalDeductions);
        existingTaxReturn.setTaxableIncome(taxableIncome);
        existingTaxReturn.setTotalFederalTax(totalFederalTax);
        existingTaxReturn.setTaxRefundOrDue(taxRefundOrDue);
        System.out.println("TaxReturn UserId after setting: " + existingTaxReturn.getUserId());

        return existingTaxReturn;

    }

    @Transactional
    public TaxReturn calculateTaxReturnsForUser(Long userId) throws JsonMappingException, IOException, JsonProcessingException {
        System.out.println("Received userId: " + userId);
        List<TaxForm> taxForms = taxFormRepository.findByUser(userId);
        if (taxForms.isEmpty()) {
            throw new IllegalArgumentException("No tax forms found: " + userId);
        }
        TaxReturn taxReturn;
        TaxReturn existingTaxReturn = taxReturnRepository.findByUserId(userId).orElse(null);

        // This is used to check if the tax return already exists for the user
        if (existingTaxReturn != null) {
            taxReturn = existingTaxReturn;
        } else {
            taxReturn = new TaxReturn();
        }

        // This is used to alculate tax return for each tax form per user
        for (TaxForm taxForm : taxForms) {
            TaxFormDetails details = TaxFormDetails.fromJson(taxForm.getFormDetails());
            taxReturn = calculatorTaxReturn(details, userId, taxReturn);
        }
        System.out.println("Saving TaxReturn with UserID: " + taxReturn.getUserId());

        return taxReturnRepository.save(taxReturn);
    }

    // Standard deduction
    private BigDecimal calculateStandardDeduction() {
        return new BigDecimal("14600");
    }

    private BigDecimal calculateFederalTax(BigDecimal taxableIncome) {

        BigDecimal federalTax = BigDecimal.ZERO;
        // Federal tax brackets for 2024
        BigDecimal[] taxBrackets = new BigDecimal[] {
            new BigDecimal("11600"),
            new BigDecimal("47150"),
            new BigDecimal("100525"),
            new BigDecimal("191950"),
            new BigDecimal("243725"),
            new BigDecimal("609350")
        };
        // Federal tax rates for 2024
        BigDecimal[] taxRates = new BigDecimal[] {
            new BigDecimal("0.10"),
            new BigDecimal("0.12"),
            new BigDecimal("0.22"),
            new BigDecimal("0.24"),
            new BigDecimal("0.32"),
            new BigDecimal("0.35"),
            new BigDecimal("0.37")
        };

        BigDecimal previousLimit = BigDecimal.ZERO;

        for (int i = 0; i < taxBrackets.length; i++) {
            // This is used to check if the taxable income is more than the lower limit of the bracket
            if (taxableIncome.compareTo(previousLimit) > 0) {
                BigDecimal maxTaxableAmount = taxableIncome.min(taxBrackets[i]).subtract(previousLimit);
                federalTax = federalTax.add(maxTaxableAmount.multiply(taxRates[i])); previousLimit = taxBrackets[i];
            } else {
                break; // This is used to break the loop if the taxable income is less than the lower limit of the bracket
            }
        }
        // This is used to calculate the tax for the highest bracket, which is greater than $609350
        if (taxableIncome.compareTo(new BigDecimal("609350")) > 0) {
            BigDecimal excess = taxableIncome.subtract(new BigDecimal("609350"));
            federalTax = federalTax.add(excess.multiply(new BigDecimal("0.37")));
        }

        return federalTax;
    }


}
