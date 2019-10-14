package com.marsrovers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.marsrovers.errorresponse.CustomErrorResponse;

@ControllerAdvice
public class RoverRestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<CustomErrorResponse> handleRoverNotFoundException(NotFoundException ex) {
		CustomErrorResponse error = new CustomErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(ex.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<CustomErrorResponse> handleWrongCoordinatesException(BadRequestException ex) {
		CustomErrorResponse error = new CustomErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<CustomErrorResponse> handleException(Exception ex) {
		CustomErrorResponse error = new CustomErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
