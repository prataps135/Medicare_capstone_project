package com.example.medicare.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(value= CustomerAlreadyExistsException.class)
	public ResponseEntity<Object> exception(CustomerAlreadyExistsException ex){
		return new ResponseEntity<Object>("Customer Already There in Database.",HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value=CustomerNotFoundExpection.class)
	public ResponseEntity<Object> exception(CustomerNotFoundExpection ex){
		return new ResponseEntity<Object>("Customer Not Found in Database.",HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value=LoginException.class)
	public ResponseEntity<Object> exception(LoginException ex){
		return new ResponseEntity<Object>("Invalid, Please try again.",HttpStatus.CONFLICT);
	}
}
