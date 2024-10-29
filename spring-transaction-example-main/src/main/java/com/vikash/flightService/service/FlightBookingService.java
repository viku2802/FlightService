package com.vikash.flightService.service;


import com.vikash.flightService.dto.FlightBookingAcknowledgement;
import com.vikash.flightService.dto.FlightBookingRequest;
import com.vikash.flightService.exception.InsufficientAmountException;
import com.vikash.flightService.model.PassengerInfo;
import com.vikash.flightService.model.PaymentInfo;
import com.vikash.flightService.repository.PassengerInfoRepository;
import com.vikash.flightService.repository.PaymentInfoRepository;
import com.vikash.flightService.utils.PaymentUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

//@Service
//@Component
@Repository
public class FlightBookingService {

    @Autowired
    private PassengerInfoRepository passengerInfoRepository;
    @Autowired
    private PaymentInfoRepository paymentInfoRepository;


  @Transactional(timeout = 10)
    public FlightBookingAcknowledgement bookFlightTicket(FlightBookingRequest request) {

        PassengerInfo passengerInfo = request.getPassengerInfo();
        passengerInfo = passengerInfoRepository.save(passengerInfo);

        PaymentInfo paymentInfo = request.getPaymentInfo();

        PaymentUtils.validateCreditLimit(paymentInfo.getAccountNo(), passengerInfo.getFare());
        	

        paymentInfo.setPassengerId(passengerInfo.getPid());
        paymentInfo.setAmount(passengerInfo.getFare());
        paymentInfoRepository.save(paymentInfo);
        return new FlightBookingAcknowledgement("SUCCESS", passengerInfo.getFare(), UUID.randomUUID().toString().split("-")[0], passengerInfo);

    }
  public PassengerInfo bookPassengerDetails(PassengerInfo passengerInfo) {
	 // passengerInfo.setPId(UUID.randomUUID().toString());
    
      passengerInfo = passengerInfoRepository.save(passengerInfo);

     return passengerInfo;

  }
  
  public List<PassengerInfo> getAllPasaengerDetails() {
		
	    
	      

	     return passengerInfoRepository.findAll();
	  }
  
  @Cacheable(cacheNames="PassengerInfo",key="#age")
  public List<PassengerInfo> getAllPasaengerDetails(int age) {
		return passengerInfoRepository.findAll().stream().filter(e->e.getAge()>50).collect(Collectors.toList());
		
	  }


  
  
  
  
  
  
  
}
