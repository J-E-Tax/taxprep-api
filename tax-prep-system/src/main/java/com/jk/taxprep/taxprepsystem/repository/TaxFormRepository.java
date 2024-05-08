package com.jk.taxprep.taxprepsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jk.taxprep.taxprepsystem.model.TaxForm;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxFormRepository extends JpaRepository<TaxForm, Long>{


    // @Query("SELECT * FROM tax_forms tf WHERE tf.tax_form_id = :taxFormId")
    // TaxForm findByTaxFormId(@Param("taxFormId") long tax_form_id);

    TaxForm findByTaxFormId(int taxFormId);

    // @Query("SELECT * FROM tax_forms tf WHERE tf.user_id = :userId")
    // List<TaxForm> findByUserId(@Param("userId") int user);



    List<TaxForm> findByUser(int userId);

    List<TaxForm> findByUser(Long userId);

}
