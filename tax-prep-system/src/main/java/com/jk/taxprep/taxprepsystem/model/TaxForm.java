package com.jk.taxprep.taxprepsystem.model;

import java.time.ZonedDateTime;

import org.hibernate.annotations.ColumnTransformer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tax_forms")
public class TaxForm {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tax_form_id")
    private long taxFormId;

    @Column(name = "user_id")
    private int user;

    @Column(name = "status")
    private String status;

    @Column(name = "submitted_at")
    private ZonedDateTime submittedAt = ZonedDateTime.now();

    @Column(name = "form_type")
    private String formType;

    @Column(name = "form_details", columnDefinition = "jsonb")
    @ColumnTransformer(write = "?::jsonb")
    private String formDetails;

    public long getTaxFormId() {
        return taxFormId;
    }

    public void setTaxFormId(long taxFormId) {
        this.taxFormId = taxFormId;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ZonedDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(ZonedDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public String getFormDetails() {
        return formDetails;
    }

    public void setFormDetails(String formDetails) {
        this.formDetails = formDetails;
    }

    public TaxForm() {
    }

    public TaxForm(long taxFormId, int user, String status, ZonedDateTime submittedAt, String formType,
            String formDetails) {
        this.taxFormId = taxFormId;
        this.user = user;
        this.status = status;
        this.submittedAt = submittedAt;
        this.formType = formType;
        this.formDetails = formDetails;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (taxFormId ^ (taxFormId >>> 32));
        result = prime * result + user;
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((submittedAt == null) ? 0 : submittedAt.hashCode());
        result = prime * result + ((formType == null) ? 0 : formType.hashCode());
        result = prime * result + ((formDetails == null) ? 0 : formDetails.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TaxForm other = (TaxForm) obj;
        if (taxFormId != other.taxFormId)
            return false;
        if (user != other.user)
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (submittedAt == null) {
            if (other.submittedAt != null)
                return false;
        } else if (!submittedAt.equals(other.submittedAt))
            return false;
        if (formType == null) {
            if (other.formType != null)
                return false;
        } else if (!formType.equals(other.formType))
            return false;
        if (formDetails == null) {
            if (other.formDetails != null)
                return false;
        } else if (!formDetails.equals(other.formDetails))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TaxForm [taxFormId=" + taxFormId + ", user=" + user + ", status=" + status + ", submittedAt="
                + submittedAt + ", formType=" + formType + ", formDetails=" + formDetails + "]";
    }

    
}
