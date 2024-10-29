package com.vikash.flightService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.vikash.flightService.model.PassengerInfo;
//@Repository
@Service
public interface PassengerInfoRepository extends JpaRepository<PassengerInfo,Long> {
	
	List<PassengerInfo> findByAge(int age);
}
