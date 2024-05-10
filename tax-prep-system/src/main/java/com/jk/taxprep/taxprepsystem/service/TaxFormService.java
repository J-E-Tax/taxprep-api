package com.jk.taxprep.taxprepsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.taxprep.taxprepsystem.model.TaxForm;
import com.jk.taxprep.taxprepsystem.model.User;
import com.jk.taxprep.taxprepsystem.repository.TaxFormRepository;

@Service
public class TaxFormService {
    @Autowired
    TaxFormRepository taxFormRepository;

    public List<TaxForm> findAllTaxForms(){
        return taxFormRepository.findAll();
    }

    public List<TaxForm> findTaxFormByUserId(int userId){
        return taxFormRepository.findByUser((int)userId);
    }

    public TaxForm findTaxFormByFormId(int formId){
        return taxFormRepository.findByTaxFormId(formId);
    }

    public TaxForm saveTaxForm(TaxForm taxForm){
       return taxFormRepository.save(taxForm);
    }

    public void deleteTaxForm(TaxForm taxForm){
        taxFormRepository.delete(taxForm);
    }
}
