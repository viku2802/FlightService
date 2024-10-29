package com.vikash.flightService.aop;

import java.util.Date;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.vikash.flightService.exception.InsufficientAmountException;
import com.vikash.flightService.model.PassengerInfo;



@Aspect
@Component
public class FlightServiceAspect {
	
	@Before(value="execution(* com.vikash.flightService.controller.FlightServiceController.*(..))")
	public void beforeControllerAdvice(JoinPoint joinPoint)
	{
	System.out.println("request to controller layer "+joinPoint.getSignature()+" started at "+new Date());	
		
	}
	
	@After(value="execution(* com.vikash.flightService.controller.FlightServiceController.*(..))")
	public void afterControllerAdvice(JoinPoint joinPoint)
	{
	System.out.println("request to controller layer "+joinPoint.getSignature()+" ended at "+new Date());	
		
	}
	
	@Before(value="execution(* com.vikash.flightService.service.FlightBookingService.*(..))")
	public void beforeServiceAdvice(JoinPoint joinPoint)
	{
		System.out.println("request to service layer "+joinPoint.getSignature()+" started at "+new Date());
	}

	
	@After(value="execution(* com.vikash.flightService.service.FlightBookingService.*(..))")
	public void afterServiceAdvice(JoinPoint joinPoint)
	{
		System.out.println("request to service layer "+joinPoint.getSignature()+" ended at "+new Date());
	}
	
	@AfterReturning(value="execution(* com.vikash.flightService.service.FlightBookingService.bookFlightTicket(..))",returning="passengerInfo")
	public void afterReturningAdviceForValidatePayment(JoinPoint joinPoint,PassengerInfo passengerInfo)
	{
		System.out.println("business logic to save an PassengerInfo with id "+passengerInfo.getPid());
	}
	
	@AfterThrowing(value="execution(* com.vikash.flightService.service.FlightBookingService.bookFlightTicket(..))",throwing="exception")
	public void afterThrowingAdviceForValidatePayment(JoinPoint joinPoint,InsufficientAmountException exception)
	{
		System.out.println("business logic to save an PassengerInfo threw an exception "+exception.getMessage());
	}
	
}
