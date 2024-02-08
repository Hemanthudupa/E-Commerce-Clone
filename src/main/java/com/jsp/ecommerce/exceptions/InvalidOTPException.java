package com.jsp.ecommerce.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InvalidOTPException extends RuntimeException {
	private String message;
}
