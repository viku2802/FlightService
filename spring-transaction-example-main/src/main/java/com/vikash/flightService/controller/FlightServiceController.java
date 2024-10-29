package com.vikash.flightService.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vikash.flightService.dto.FlightBookingAcknowledgement;
import com.vikash.flightService.dto.FlightBookingRequest;
import com.vikash.flightService.exception.InternalServerException;
import com.vikash.flightService.model.PassengerInfo;
import com.vikash.flightService.service.FlightBookingService;

import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
@RequestMapping("/api/flight/")
@Slf4j
public class FlightServiceController {
	  private Timer timer;

	  
          
	   
	private static Logger logger=LoggerFactory.getLogger(FlightServiceController.class);

	@Value("${database.exception}")
	private static String DATABASE_DOWN;
	
	@Autowired
	private FlightBookingService flightBookingService ;
	
	@PostMapping(value="/v1/fligtservice/" , consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<FlightBookingAcknowledgement> bookFlightTicket(@RequestBody FlightBookingRequest flightBookingRequest)
	{
		try
		{
			FlightBookingAcknowledgement flightBookingAcknowledgement=flightBookingService.bookFlightTicket(flightBookingRequest);
			//logger.trace("HTTP POST SUCCESS: data inserted to DB "+ flightBookingAcknowledgement.toString());
		    return new ResponseEntity<FlightBookingAcknowledgement>(flightBookingAcknowledgement,HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("not able to store data into DB",ex);
			if (ex instanceof DataAccessException) 
				throw new InternalServerException(DATABASE_DOWN);
			else
				throw ex;
		}
	}
	
	@PostMapping(value="/v1/passengerservice/" , consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PassengerInfo> bookPassenger(@RequestBody PassengerInfo passengerInfo)
	{
		try
		{
			PassengerInfo passengerInfoDetails=flightBookingService.bookPassengerDetails(passengerInfo);
			//logger.info("HTTP POST SUCCESS: data inserted to DB "+ passengerInfoDetails.toString());
		    return new ResponseEntity<PassengerInfo>(passengerInfoDetails,HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("not able to store data into DB",ex);
			if (ex instanceof DataAccessException) 
				throw new InternalServerException(DATABASE_DOWN);
			else
				throw ex;
		}
	}

	
	@GetMapping(value="/v1/allpassengerservice/")
	public ResponseEntity<List<PassengerInfo>> getAllPassenger()
	{
		try
		{
			List<PassengerInfo> passengerInfoDetails=flightBookingService.getAllPasaengerDetails();
			//logger.info("HTTP POST SUCCESS: data inserted to DB "+ passengerInfoDetails.toString());
		    return new ResponseEntity<List<PassengerInfo>>(passengerInfoDetails,HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("not able to store data into DB",ex);
			if (ex instanceof DataAccessException) 
				throw new InternalServerException(DATABASE_DOWN);
			else
				throw ex;
		}
	}
	

    //@Timed(value = "greetings.timer", description = "Time taken to process my API endpoint")
	@GetMapping(value="/v1/passengerdetailsbyage/{age}")
	public ResponseEntity<List<PassengerInfo>> getAllPassengerbyPid(@PathVariable("age") int age)
	{
    	Timer.Sample sample = Timer.start();
		try
		{
			List<PassengerInfo> passengerInfoDetails=flightBookingService.getAllPasaengerDetails(age);
			//logger.info("HTTP POST SUCCESS: data inserted to DB "+ passengerInfoDetails.toString());
	       // double responseTimeInMilliSeconds = timer.record(() -> sample.stop(timer) / 1000000);

	        //System.out.println("Greetings API response time: " + responseTimeInMilliSeconds + " ms");
		    return new ResponseEntity<List<PassengerInfo>>(passengerInfoDetails,HttpStatus.OK);
		}
		catch(Exception ex)
		{
			logger.error("not able to store data into DB",ex);
			if (ex instanceof DataAccessException) 
				throw new InternalServerException(DATABASE_DOWN);
			else
				throw ex;
		}
	}
	
	@Value("${spring.message}")
	private String message;
	@GetMapping(value="/v1/check")
	public String check()
	{
		return message;
    	
	}
}
