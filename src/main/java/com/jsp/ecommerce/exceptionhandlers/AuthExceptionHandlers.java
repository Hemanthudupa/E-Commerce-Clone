package com.jsp.ecommerce.exceptionhandlers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.ecommerce.exceptions.DuplicateEmailException;
import com.jsp.ecommerce.exceptions.InvalidEmailException;
import com.jsp.ecommerce.exceptions.InvalidOTPException;
import com.jsp.ecommerce.exceptions.UserEmailAlreadyVerifiedException;
import com.jsp.ecommerce.exceptions.UserNotLoggedInException;
import com.jsp.ecommerce.exceptions.UserSessionExpiredException;
import com.jsp.ecommerce.exceptions.WrongOTPException;

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

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<Object> usernameNotFoundException(UsernameNotFoundException ex) {
		return exceptionStructure(ex.getMessage(), HttpStatus.BAD_REQUEST,
				"username or email not found in the DATA BASE!!!!");
	}

	@ExceptionHandler(InvalidEmailException.class)
	public ResponseEntity<Object> invalidEmailException(InvalidEmailException ex) {
		return exceptionStructure(ex.getMessage(), HttpStatus.BAD_REQUEST,
				" email cannot be empty , please give valid email !!!!");
	}

	@ExceptionHandler(InvalidOTPException.class)
	public ResponseEntity<Object> invalidOTPException(InvalidOTPException ex) {
		return exceptionStructure(ex.getMessage(), HttpStatus.BAD_REQUEST,
				"OTP cannot be null  , provide an valid OTP!!!!");
	}

	@ExceptionHandler(WrongOTPException.class)
	public ResponseEntity<Object> wrongOTPException(WrongOTPException ex) {
		return exceptionStructure(ex.getMessage(), HttpStatus.BAD_REQUEST,
				"invalid otp , you have mentioned wrong OTP !!!!");
	}

	@ExceptionHandler(UserSessionExpiredException.class)
	public ResponseEntity<Object> userSessionExpiredException(UserSessionExpiredException ex) {
		return exceptionStructure(ex.getMessage(), HttpStatus.BAD_REQUEST,
				"user expired , please regester user again  !!!!");
	}

	@ExceptionHandler(UserNotLoggedInException.class)
	public ResponseEntity<Object> userNotLoggedInException(UserNotLoggedInException ex) {
		return exceptionStructure(ex.getMessage(), HttpStatus.BAD_REQUEST,
				"user not logged in to logout  , please login if you want to logout   !!!!");
	}

}