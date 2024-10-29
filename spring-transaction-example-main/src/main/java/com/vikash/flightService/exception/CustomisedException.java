package com.vikash.flightService.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;





@ControllerAdvice
@RestController
public class CustomisedException extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(InternalServerException.class)
	public final ResponseEntity<Object> handleAllException(Exception ex,WebRequest request)
	{
		Response exceptionResponse = new Response(new Date(),HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);		
	}
	@ExceptionHandler(InsufficientAmountException.class)
	public final ResponseEntity<Object> handleAllException(InsufficientAmountException ex,WebRequest request)
	{
		Response exceptionResponse = new Response(new Date(),HttpStatus.INSUFFICIENT_STORAGE.value(),HttpStatus.INSUFFICIENT_STORAGE,ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);		
	}
	

}
