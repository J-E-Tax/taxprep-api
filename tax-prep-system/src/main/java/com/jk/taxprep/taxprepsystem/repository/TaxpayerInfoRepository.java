package com.jk.taxprep.taxprepsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jk.taxprep.taxprepsystem.model.TaxpayerInfo;

public interface TaxpayerInfoRepository extends JpaRepository<TaxpayerInfo, Long> {
    TaxpayerInfo findByUser_UserId(Long userId);

}
