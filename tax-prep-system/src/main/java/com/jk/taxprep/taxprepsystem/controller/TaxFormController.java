package com.jk.taxprep.taxprepsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jk.taxprep.taxprepsystem.model.TaxForm;
import com.jk.taxprep.taxprepsystem.service.TaxFormService;

@RestController
@RequestMapping("/taxform")
public class TaxFormController {
    @Autowired
    TaxFormService taxFormService;


    @GetMapping("/taxes")
    public ResponseEntity<List<TaxForm>> findAllTaxForms(){
        List<TaxForm> allTaxes = taxFormService.findAllTaxForms();
        return new ResponseEntity<List<TaxForm>>(allTaxes, HttpStatus.OK);
    }

    @GetMapping("/taxes/users/{userId}")
    public ResponseEntity<List<TaxForm>> findTaxFormByUserId(@PathVariable int userId){
        List<TaxForm> userTaxes = taxFormService.findTaxFormByUserId(userId);
        return new ResponseEntity<List<TaxForm>>(userTaxes, HttpStatus.OK);
    }

    @GetMapping("/taxes/form/{formId}")
    public ResponseEntity<TaxForm> findTaxFormByFormId(@PathVariable int formId){
        TaxForm formTaxes = taxFormService.findTaxFormByFormId(formId);
        return new ResponseEntity<TaxForm>(formTaxes, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<TaxForm> createTaxForm(@RequestBody TaxForm taxForm){
        TaxForm newTaxForm = taxFormService.saveTaxForm(taxForm);
        return new ResponseEntity<TaxForm>(newTaxForm, HttpStatus.OK);
    }

    @PutMapping("/taxes/update")
    public ResponseEntity<TaxForm> updateTaxForm(@RequestBody TaxForm taxForm){
        TaxForm newTaxForm = taxFormService.saveTaxForm(taxForm);
        return new ResponseEntity<TaxForm>(newTaxForm, HttpStatus.OK);
    }

    @DeleteMapping("/taxes")
    public ResponseEntity<TaxForm> deleteTaxForm(@RequestBody TaxForm taxForm){
        taxFormService.deleteTaxForm(taxForm);
        return ResponseEntity.noContent().build();
    }
}
