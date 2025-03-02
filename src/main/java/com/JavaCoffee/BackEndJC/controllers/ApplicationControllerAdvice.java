package com.JavaCoffee.BackEndJC.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.JavaCoffee.BackEndJC.exception.ProductNotFoundException;
import com.JavaCoffee.BackEndJC.exception.RecordNotFoundException;

@RestControllerAdvice 
public class ApplicationControllerAdvice {

	@ExceptionHandler(RecordNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleNotFoundException(RecordNotFoundException ex) {
		return "Error: " + ex.getMessage();
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	private ResponseEntity<String> eventNotFoundHandler(ProductNotFoundException exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
	}
	
}
