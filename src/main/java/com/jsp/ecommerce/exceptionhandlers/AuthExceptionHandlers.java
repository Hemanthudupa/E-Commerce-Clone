package com.jsp.ecommerce.exceptionhandlers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.ecommerce.exceptions.DuplicateEmailException;
import com.jsp.ecommerce.exceptions.UserEmailAlreadyVerifiedException;

@RestControllerAdvice
public class AuthExceptionHandlers {

	public ResponseEntity<Object> exceptionStructure(String message, HttpStatus status, Object data) {
		return new ResponseEntity<Object>(Map.of("message", message, "status", status.value(), "data", data),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<Object> duplicateEmailException(DuplicateEmailException ex) {
		return exceptionStructure(ex.getMessage(), HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
	}

	@ExceptionHandler(UserEmailAlreadyVerifiedException.class)
	public ResponseEntity<Object> userEmailAlreadyVerifiedException(UserEmailAlreadyVerifiedException ex) {
		return exceptionStructure(ex.getMessage(), HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
	}

}