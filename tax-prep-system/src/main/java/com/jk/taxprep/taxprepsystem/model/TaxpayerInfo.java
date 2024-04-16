package com.jk.taxprep.taxprepsystem.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "taxpayer_info")
public class TaxpayerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taxpayerInfoId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    private String firstName;
    private String middleName;
    private String lastName;
    private String occupation;
    private String ssn;
    private LocalDate dateOfBirth;
    private String phoneNumber;

    @Column(columnDefinition = "jsonb")
    private String address;

    public TaxpayerInfo() {}

    public TaxpayerInfo(User user, String firstName, String middleName, String lastName, String occupation, String ssn, LocalDate dateOfBirth, String phoneNumber, String address) {
        this.user = user;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.occupation = occupation;
        this.ssn = ssn;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Long getTaxpayerInfoId() {
        return taxpayerInfoId;
    }

    public void setTaxpayerInfoId(Long taxpayerInfoId) {
        this.taxpayerInfoId = taxpayerInfoId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
