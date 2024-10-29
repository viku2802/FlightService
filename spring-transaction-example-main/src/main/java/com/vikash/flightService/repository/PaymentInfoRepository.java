package com.vikash.flightService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikash.flightService.model.PaymentInfo;

public interface PaymentInfoRepository extends JpaRepository<PaymentInfo,String> {
}
