package com.jk.taxprep.taxprepsystem.model;

import java.math.BigDecimal;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.io.IOException;

public class TaxFormDetails {
    @JsonProperty("a")
    private String ssn;
    @JsonProperty("b")
    private String employerIdentificationNumber;
    @JsonProperty("c")
    private String employerDetails;
    @JsonProperty("d")
    private String controlNumber;
    @JsonProperty("e")
    private String employeeName;
    @JsonProperty("f")
    private String employeeAddress;
    @JsonProperty("l1")
    private BigDecimal wages;
    @JsonProperty("l2")
    private BigDecimal federalIncomeTaxWithheld;
    @JsonProperty("l3")
    private BigDecimal socialSecurityWages;
    @JsonProperty("l4")
    private BigDecimal socialSecurityTaxWithheld;
    @JsonProperty("l5")
    private BigDecimal medicareWagesAndTips;
    @JsonProperty("l6")
    private BigDecimal medicareTaxWithheld;
    @JsonProperty("l7")
    private BigDecimal socialSecurityTips;
    @JsonProperty("l8")
    private BigDecimal allocatedTips;
    @JsonProperty("l10")
    private BigDecimal dependentCareBenefits;
    @JsonProperty("l11")
    private BigDecimal nonqualifiedPlans;
    @JsonProperty("l12")
    private Map<String, BigDecimal> additionalTaxes;
    @JsonProperty("l13")
    private Map<String, Boolean> checkboxes;
    @JsonProperty("l14")
    private String otherInfo;

    public TaxFormDetails() {}

    public TaxFormDetails(String ssn, String employerIdentificationNumber, String employerDetails, String controlNumber, String employeeName, String employeeAddress, BigDecimal wages, BigDecimal federalIncomeTaxWithheld, BigDecimal socialSecurityWages, BigDecimal socialSecurityTaxWithheld, BigDecimal medicareWagesAndTips, BigDecimal medicareTaxWithheld, BigDecimal socialSecurityTips, BigDecimal allocatedTips, BigDecimal dependentCareBenefits, BigDecimal nonqualifiedPlans, Map<String, BigDecimal> additionalTaxes, Map<String, Boolean> checkboxes, String otherInfo) {
        this.ssn = ssn;
        this.employerIdentificationNumber = employerIdentificationNumber;
        this.employerDetails = employerDetails;
        this.controlNumber = controlNumber;
        this.employeeName = employeeName;
        this.employeeAddress = employeeAddress;
        this.wages = wages;
        this.federalIncomeTaxWithheld = federalIncomeTaxWithheld;
        this.socialSecurityWages = socialSecurityWages;
        this.socialSecurityTaxWithheld = socialSecurityTaxWithheld;
        this.medicareWagesAndTips = medicareWagesAndTips;
        this.medicareTaxWithheld = medicareTaxWithheld;
        this.socialSecurityTips = socialSecurityTips;
        this.allocatedTips = allocatedTips;
        this.dependentCareBenefits = dependentCareBenefits;
        this.nonqualifiedPlans = nonqualifiedPlans;
        this.additionalTaxes = additionalTaxes;
        this.checkboxes = checkboxes;
        this.otherInfo = otherInfo;
    }

    // This method is used to convert the JSON string to a TaxFormDetails object
    public static TaxFormDetails fromJson(String json) throws IOException, JsonMappingException, JsonProcessingException {
        return new ObjectMapper().readValue(json, TaxFormDetails.class);
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getEmployerIdentificationNumber() {
        return employerIdentificationNumber;
    }

    public void setEmployerIdentificationNumber(String employerIdentificationNumber) {
        this.employerIdentificationNumber = employerIdentificationNumber;
    }

    public String getEmployerDetails() {
        return employerDetails;
    }

    public void setEmployerDetails(String employerDetails) {
        this.employerDetails = employerDetails;
    }

    public String getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public BigDecimal getWages() {
        return wages;
    }

    public void setWages(BigDecimal wages) {
        this.wages = wages;
    }

    public BigDecimal getFederalIncomeTaxWithheld() {
        return federalIncomeTaxWithheld;
    }

    public void setFederalIncomeTaxWithheld(BigDecimal federalIncomeTaxWithheld) {
        this.federalIncomeTaxWithheld = federalIncomeTaxWithheld;
    }

    public BigDecimal getSocialSecurityWages() {
        return socialSecurityWages;
    }

    public void setSocialSecurityWages(BigDecimal socialSecurityWages) {
        this.socialSecurityWages = socialSecurityWages;
    }

    public BigDecimal getSocialSecurityTaxWithheld() {
        return socialSecurityTaxWithheld;
    }

    public void setSocialSecurityTaxWithheld(BigDecimal socialSecurityTaxWithheld) {
        this.socialSecurityTaxWithheld = socialSecurityTaxWithheld;
    }

    public BigDecimal getMedicareWagesAndTips() {
        return medicareWagesAndTips;
    }

    public void setMedicareWagesAndTips(BigDecimal medicareWagesAndTips) {
        this.medicareWagesAndTips = medicareWagesAndTips;
    }

    public BigDecimal getMedicareTaxWithheld() {
        return medicareTaxWithheld;
    }

    public void setMedicareTaxWithheld(BigDecimal medicareTaxWithheld) {
        this.medicareTaxWithheld = medicareTaxWithheld;
    }

    public BigDecimal getSocialSecurityTips() {
        return socialSecurityTips;
    }

    public void setSocialSecurityTips(BigDecimal socialSecurityTips) {
        this.socialSecurityTips = socialSecurityTips;
    }

    public BigDecimal getAllocatedTips() {
        return allocatedTips;
    }

    public void setAllocatedTips(BigDecimal allocatedTips) {
        this.allocatedTips = allocatedTips;
    }

    public BigDecimal getDependentCareBenefits() {
        return dependentCareBenefits;
    }

    public void setDependentCareBenefits(BigDecimal dependentCareBenefits) {
        this.dependentCareBenefits = dependentCareBenefits;
    }

    public BigDecimal getNonqualifiedPlans() {
        return nonqualifiedPlans;
    }

    public void setNonqualifiedPlans(BigDecimal nonqualifiedPlans) {
        this.nonqualifiedPlans = nonqualifiedPlans;
    }

    public Map<String, BigDecimal> getAdditionalTaxes() {
        return additionalTaxes;
    }

    public void setAdditionalTaxes(Map<String, BigDecimal> additionalTaxes) {
        this.additionalTaxes = additionalTaxes;
    }

    public Map<String, Boolean> getCheckboxes() {
        return checkboxes;
    }

    public void setCheckboxes(Map<String, Boolean> checkboxes) {
        this.checkboxes = checkboxes;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }


}
