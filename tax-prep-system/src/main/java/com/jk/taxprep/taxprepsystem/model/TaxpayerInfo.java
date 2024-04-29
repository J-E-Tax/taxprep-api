package com.jk.taxprep.taxprepsystem.model;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnTransformer;

import com.fasterxml.jackson.annotation.JsonFormat;

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

    @Column(name = "date_of_birth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dob;
    private String phoneNumber;

    @ColumnTransformer(write = "?::jsonb")
    private String address;

    public TaxpayerInfo() {}

    public TaxpayerInfo(User user, String firstName, String middleName, String lastName, String occupation, String ssn, LocalDate dob, String phoneNumber, String address) {
        this.user = user;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.occupation = occupation;
        this.ssn = ssn;
        this.dob = dob;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
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
