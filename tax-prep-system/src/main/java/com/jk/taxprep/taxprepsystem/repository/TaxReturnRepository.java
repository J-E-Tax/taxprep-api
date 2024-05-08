package com.jk.taxprep.taxprepsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jk.taxprep.taxprepsystem.model.TaxReturn;

@Repository
public interface TaxReturnRepository extends JpaRepository<TaxReturn, Long>{
    List<TaxReturn> findByUserId(Long userId);
}
