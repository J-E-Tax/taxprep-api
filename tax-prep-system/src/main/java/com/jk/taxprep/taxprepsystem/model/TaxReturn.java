package com.jk.taxprep.taxprepsystem.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tax_returns")
public class TaxReturn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taxReturnId;
    private Long userId;
    private BigDecimal totalIncome;
    private BigDecimal totalDeductions;
    private BigDecimal taxableIncome;
    private BigDecimal totalFederalTax;
    private BigDecimal taxRefundOrDue;
    private String status;

    public TaxReturn() {}

    public TaxReturn(Long userId, BigDecimal totalIncome, BigDecimal totalDeductions, BigDecimal taxableIncome, BigDecimal totalFederalTax, BigDecimal taxRefundOrDue, String status) {
        this.userId = userId;
        this.totalIncome = totalIncome;
        this.totalDeductions = totalDeductions;
        this.taxableIncome = taxableIncome;
        this.totalFederalTax = totalFederalTax;
        this.taxRefundOrDue = taxRefundOrDue;
        this.status = status;
    }

    public Long getTaxReturnId() {
        return taxReturnId;
    }

    public void setTaxReturnId(Long taxReturnId) {
        this.taxReturnId = taxReturnId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public BigDecimal getTotalDeductions() {
        return totalDeductions;
    }

    public void setTotalDeductions(BigDecimal totalDeductions) {
        this.totalDeductions = totalDeductions;
    }

    public BigDecimal getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(BigDecimal taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public BigDecimal getTotalFederalTax() {
        return totalFederalTax;
    }

    public void setTotalFederalTax(BigDecimal totalFederalTax) {
        this.totalFederalTax = totalFederalTax;
    }

    public BigDecimal getTaxRefundOrDue() {
        return taxRefundOrDue;
    }

    public void setTaxRefundOrDue(BigDecimal taxRefundOrDue) {
        this.taxRefundOrDue = taxRefundOrDue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}


