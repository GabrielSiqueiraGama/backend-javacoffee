package com.JavaCoffee.BackEndJC.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.JavaCoffee.BackEndJC.exception.ProductNotFoundException;
import com.JavaCoffee.BackEndJC.exception.RecordNotFoundException;

import jakarta.validation.ConstraintViolationException;

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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		return exception.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + " "  + error.getDefaultMessage())
				.reduce("", (acc, error) -> acc + error + "\n");
	}
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleConstraintViolationException(ConstraintViolationException exception) {
		return exception.getConstraintViolations().stream().map(error -> error.getPropertyPath() + " " + error.getMessage())
				.reduce("", (acc, error) -> acc + error + "\n");
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
		return exception.getName() + "should be of type" + exception.getRequiredType().getName();
	}
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleNullPointerException(NullPointerException ex) {
		String errorMessage = ex.getMessage();
		if (errorMessage != null && errorMessage.contains("Cannot invoke") && errorMessage.contains("because the return value of")) {
			int startIndex = errorMessage.indexOf("return value of \"") + "return value of \"".length();
			int endIndex = errorMessage.indexOf("()\"", startIndex);

			if (startIndex != -1 && endIndex != -1) {
				String fullMethodPath = errorMessage.substring(startIndex, endIndex);
				int lastDotIndex = fullMethodPath.lastIndexOf('.');
				if (lastDotIndex != -1) {
					String fieldName = fullMethodPath.substring(lastDotIndex + 1);
					return "Erro: O campo '" + fieldName + "' não pode ser nulo.";
				}
			}
		}
		return "Erro: Um campo obrigatório está faltando ou é nulo. Detalhes: " + ex.getMessage();
	}
}
