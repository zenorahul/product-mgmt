package com.rahul.productmgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalControllerAdvice {

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<String> invalidUrl(NoResourceFoundException noResourceFoundException) {
		return new ResponseEntity<String>("API URL is incorrect", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> badJsonRequest(HttpMessageNotReadableException httpMessageNotReadableException) {
		return new ResponseEntity<String>("Incorrect JSON request", HttpStatus.BAD_REQUEST);
	}
}