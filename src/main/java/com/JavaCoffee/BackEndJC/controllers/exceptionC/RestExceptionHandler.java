package com.JavaCoffee.BackEndJC.controllers.exceptionC;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.JavaCoffee.BackEndJC.exception.ProductNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ProductNotFoundException.class)
	private ResponseEntity<String> eventNotFoundHandler(ProductNotFoundException exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
		
	}

}
