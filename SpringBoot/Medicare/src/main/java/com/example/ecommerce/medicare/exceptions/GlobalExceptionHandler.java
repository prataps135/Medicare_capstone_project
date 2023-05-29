package com.example.ecommerce.medicare.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value= CustomerExistException.class)
	public ResponseEntity<Object> exception( CustomerExistException exception) {
		return new ResponseEntity<>("You are already registered", HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value= CustomerNotFoundException.class)
	public ResponseEntity<Object> exception( CustomerNotFoundException exception) {
		return new ResponseEntity<>("No Such Customer Exist", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value= LoginException.class)
	public ResponseEntity<Object> exception( LoginException exception) {
		return new ResponseEntity<>("Invalid, Please Try again", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value= AdminExistException.class)
	public ResponseEntity<Object> exception( AdminExistException exception) {
		return new ResponseEntity<>("Admin Exist already", HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value= AdminNotFoundException.class)
	public ResponseEntity<Object> exception( AdminNotFoundException exception) {
		return new ResponseEntity<>("Admin not found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value= ProductNotFound.class)
	public ResponseEntity<Object> exception( ProductNotFound exception) {
		return new ResponseEntity<>("No such product exist", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value= ProductExistException.class)
	public ResponseEntity<Object> exception( ProductExistException exception) {
		return new ResponseEntity<>("Product already exist", HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value= UpdateProductException.class)
	public ResponseEntity<Object> exception( UpdateProductException exception) {
		return new ResponseEntity<>("Cannot update product not found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value= DeleteProductException.class)
	public ResponseEntity<Object> exception( DeleteProductException exception) {
		return new ResponseEntity<>("Cannot delete product not found", HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(value= CategoryException.class)
	public ResponseEntity<Object> exception(  CategoryException exception) {
		return new ResponseEntity<>("No such category exist", HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
