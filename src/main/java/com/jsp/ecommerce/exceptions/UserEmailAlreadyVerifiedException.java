package com.jsp.ecommerce.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserEmailAlreadyVerifiedException extends RuntimeException {
	private String message;
}
