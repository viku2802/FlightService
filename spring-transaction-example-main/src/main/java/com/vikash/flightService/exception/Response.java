package com.vikash.flightService.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Data;
@Data
public class Response {
	private String details;
	public Response(Date timestamp, int status,HttpStatus httpStatus,String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.status=status;
		this.httpStatus=httpStatus;
	}
	private String message;
	private int status;
	private Date timestamp;
	private HttpStatus httpStatus;

}
